package com.xjjlearning.springframework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;

/**
 * created by xjj on 2022/12/6
 */
@Configuration
@EnableWebSecurity(debug = true)
public class Oauth2AuthorizationServerSecurityConfiguration {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0

        // @formatter:off
        http
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        // @formatter:on
        return http.build();
    }

    @Bean
    RegisteredClientRepository registeredClientRepository() {
        RegisteredClient loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("login-client")
                .clientSecret("{noop}openid-connect")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // it's our white list which is 在登录和授权之后回调的用户端地址, 其中回调地址还需要用户指定
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/login-client")
                .redirectUri("http://127.0.0.1:8080/authorized")
                .scope(OidcScopes.OPENID) // openid connect necessary
                .scope(OidcScopes.PROFILE) // openid necessary
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        // RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        //         .clientId("messaging-client")
        //         .clientSecret("{noop}secret")
        //         .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        //         // No user authorization is required, client
        //         // The client initiates a request to the authorization server in its own name to obtain user resources
        //         .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        //         .scope("message:read")
        //         .scope("message:write")
        //         .build();
        return new InMemoryRegisteredClientRepository(loginClient);
    }

    //
    //     // JwtAuthenticationProvider use it
    // @Bean
    // JwtDecoder jwtDecoder(KeyPair keyPair) {
    //     return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    // }
    //
    // @Bean
    // public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
    //     RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    //     RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    //     // @formatter:off
    //     RSAKey rsaKey = new RSAKey.Builder(publicKey)
    //             .privateKey(privateKey)
    //             .keyID(UUID.randomUUID().toString())
    //             .build();
    //     // @formatter:on
    //     JWKSet jwkSet = new JWKSet(rsaKey);
    //     return new ImmutableJWKSet<>(jwkSet);
    // }
    //
    // @Bean
    // public ProviderSettings providerSettings() {
    //     return ProviderSettings.builder().issuer("http://localhost:9000").build();
    // }
    // @Bean
    // @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    // KeyPair generateRsaKey() {
    //     KeyPair keyPair;
    //     try {
    //         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    //         keyPairGenerator.initialize(2048);
    //         keyPair = keyPairGenerator.generateKeyPair();
    //     }
    //     catch (Exception ex) {
    //         throw new IllegalStateException(ex);
    //     }
    //     return keyPair;
    // }
}
