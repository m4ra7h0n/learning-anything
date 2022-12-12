package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.handler.SimpleAccessDeniedHandler;
import com.xjjlearning.springframework.security.handler.SimpleAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by xjj on 2022/12/12
 */
@Configuration
public class ExceptionConfiguration {
    @Bean
    public SimpleAccessDeniedHandler simpleAccessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }
    @Bean
    public SimpleAuthenticationEntryPoint simpleAuthenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }
}
