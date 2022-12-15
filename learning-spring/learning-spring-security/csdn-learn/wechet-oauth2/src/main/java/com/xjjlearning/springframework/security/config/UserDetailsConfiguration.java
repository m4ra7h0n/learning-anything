package com.xjjlearning.springframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * created by xjj on 2022/12/14
 */
@Configuration
public class UserDetailsConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return username -> User.withUsername("xjj")
                .password("12345")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .build();
    }
}
