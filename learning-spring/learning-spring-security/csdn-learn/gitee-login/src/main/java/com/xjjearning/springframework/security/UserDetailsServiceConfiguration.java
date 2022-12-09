package com.xjjearning.springframework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * created by xjj on 2022/12/9
 */
@EnableWebSecurity(debug = true)
public class UserDetailsServiceConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return username -> User.withUsername("xjj-jjj")
                .password("xiaozijian2000")
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .build();
    }
}
