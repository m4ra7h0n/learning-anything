package com.xjjlearning.springframework.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * created by xjj on 2022/12/6
 */
@Configuration
@EnableWebSecurity(debug = true)
public class RestConfig {
    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }


    // must
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // AuthenticationManager
        // AbstractAuthenticationProcessingFilter
        // UsernamePasswordAuthenticationFilter
        // BasicAuthenticationFilter
        // AuthorizationFilter

        // filter 1
        // HeaderFilter

        // filter 2
        // -> csrfFilter

        // filter 3
        // -> LogoutFilter

        // filter 4
        // -> AbstractAuthenticationProcessingFilter

        // filter 5
        //-> DefaultLoginPageGeneratingFilter

        // filter 6
        //-> DefaultLogoutPageGeneratingFilter

        // filter 7
        //-> BasicAuthenticationFilter
        // providerManager -> traverse providers

        // filter 8
        // RequestCacheAwareFilter
        // SecurityContextHolderAwareRequestFilter
        // AnonymousAuthenticationFilter
        // SessionManagementFilter

        // ExceptionTranslationFilter
        // AuthorizationFilter


        // filter 1
        //-> BearerTokenAuthenticationFilter
        // token = this.bearerTokenResolver.resolve((HttpRequest) request);
        // BearerTokenAuthenticationToken authenticationRequest = new BearerTokenAuthenticationToken(token);
        // Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);


        // settings for FilterChains



        http.authorizeHttpRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET, "/message/**").hasAnyAuthority("SCOPE_message:read")
                        .antMatchers(HttpMethod.POST, "/message/**").hasAnyAuthority("SCOPE_message:write")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()) // username/password
                .csrf((csrf) -> csrf.ignoringAntMatchers("/token")) // use our jwt. Or 403 forbidden
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)

                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );
        return http.build();
    }

    @Bean
    UserDetailsService users() {
        // @formatter:off
        return new InMemoryUserDetailsManager(
                User.withUsername("username")
                        .password("{noop}password")
                        .authorities("app")
                        .build()
        );
        // @formatter:on
    }

}
