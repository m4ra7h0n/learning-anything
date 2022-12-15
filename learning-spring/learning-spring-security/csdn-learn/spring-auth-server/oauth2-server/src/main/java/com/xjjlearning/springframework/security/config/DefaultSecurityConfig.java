package com.xjjlearning.springframework.security.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;

/**
 * created by xjj on 2022/12/14
 */
@EnableWebSecurity(debug = true)
public class DefaultSecurityConfig {
    @SneakyThrows
    @Bean
    JwtDecoder jwtDecoder() {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        // 读取cer公钥证书来配置解码器
        ClassPathResource resource = new ClassPathResource("pub.cer");
        Certificate certificate = certificateFactory.generateCertificate(resource.getInputStream());
        RSAPublicKey publicKey = (RSAPublicKey) certificate.getPublicKey();
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    // @formatter:off
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests.antMatchers("/oauth2/user")
                                .hasAnyAuthority("USERINFO")
                                .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .oauth2ResourceServer().jwt();
        return http.build();
    }
    // @formatter:on

    // @formatter:off
    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder()
                .username("xjj")
                .password("12345")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    // @formatter:on
}
