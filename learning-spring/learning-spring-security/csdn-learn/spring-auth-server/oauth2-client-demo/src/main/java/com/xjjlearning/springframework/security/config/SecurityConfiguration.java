package com.xjjlearning.springframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by xjj on 2022/12/14
 */
@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain clientSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                // .antMatchers("/foo/bar").anonymous()
                .antMatchers("/foo/bar")
                .hasAnyAuthority("ROLE_ANONYMOUS","SCOPE_userinfo")
        );
        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client(Customizer.withDefaults());
        return http.build();
    }
}
