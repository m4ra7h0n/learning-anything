package com.xjjlearning.springframework.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class OAuth2ClientSecurityConfig {
    //
    // // @Bean
    // // WebSecurityCustomizer webSecurityCustomizer() {
    // //     return (web) -> web.ignoring().requestMatchers("/webjars/**");
    // // }
    //
    // // @formatter:off
    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .authorizeHttpRequests(authorize ->
    //                     authorize.anyRequest().authenticated()
    //             )
    //             .oauth2Login(oauth2Login ->
    //                     oauth2Login.loginPage("/oauth2/authorization/messaging-client-oidc"))
    //             .oauth2Client(withDefaults());
    //     return http.build();
    // }
    // // @formatter:on

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**");
    }

    // @formatter:off
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().authenticated()
                )
                // client login page
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/messaging-client-oidc"))
                .oauth2Client(withDefaults());
        return http.build();
    }
    // @formatter:on




}