package com.xjjlearning.springframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * created by xjj on 2022/12/14
 */
@EnableWebSecurity
public class UserDetailsServiceConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return username -> User.withUsername("xjj")
                .password("123456")
                // .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .build();
    }
}
