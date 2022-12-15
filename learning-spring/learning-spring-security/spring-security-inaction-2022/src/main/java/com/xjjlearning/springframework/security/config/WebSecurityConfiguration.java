package com.xjjlearning.springframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by xjj on 2022/12/13
 */
@Configuration
public class WebSecurityConfiguration {
    /**
     * Admin过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        // http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        http
                // 当访问这些路径的时候使用此 SecurityFilterChain
                .requestMatchers((requestMatchers) -> requestMatchers
                        .mvcMatchers("/admin/**")
                        .mvcMatchers("/login")
                )
                // 对请求的路径进行授权
                .authorizeRequests((authorizeRequests) -> authorizeRequests
                        .antMatchers("/login").permitAll()
                        .antMatchers("/admin/hello/**").hasRole("ADMIN")
                        .antMatchers("/admin/super/**").hasRole("SUPER")
                        // .mvcMatchers("/**").access("hasRole('ROLE_ADMIN')")
                        // .mvcMatchers("/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }

    /**
     * app过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .requestMatchers(requestMatchers -> requestMatchers
                        .mvcMatchers("/app/**"))
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }
}
