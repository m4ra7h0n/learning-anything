package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.filter.JwtAuthenticationFilter;
import com.xjjlearning.springframework.security.handler.CustomLoginFailedHandler;
import com.xjjlearning.springframework.security.handler.CustomLoginSuccessHandler;
import com.xjjlearning.springframework.security.jwt.JwtProperties;
import com.xjjlearning.springframework.security.jwt.JwtTokenCacheStorage;
import com.xjjlearning.springframework.security.jwt.JwtTokenGenerator;
import com.xjjlearning.springframework.security.jwt.JwtTokenStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * JwtConfiguration
 *
 * @author Felordcn
 * @since 16 :54 2019/10/25
 */
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "jwt.config", name = "enabled")
@Configuration
public class JwtConfiguration {


    /**
     * Jwt token storage .
     *
     * @return the jwt token storage
     */
    @Bean
    public JwtTokenStorage jwtTokenStorage() {
        return new JwtTokenCacheStorage();
    }


    /**
     * Jwt token generator.
     *
     * @param jwtTokenStorage the jwt token storage
     * @param jwtProperties   the jwt properties
     * @return the jwt token generator
     */
    @Bean
    public JwtTokenGenerator jwtTokenGenerator(JwtTokenStorage jwtTokenStorage, JwtProperties jwtProperties) {
        return new JwtTokenGenerator(jwtTokenStorage, jwtProperties);
    }

    /**
     * 处理登录成功后返回 JWT Token 对.
     *
     * @param jwtTokenGenerator the jwt token generator
     * @return the authentication success handler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(JwtTokenGenerator jwtTokenGenerator) {
        return new CustomLoginSuccessHandler(jwtTokenGenerator);
    }

    /**
     * 失败登录处理器 处理登录失败后的逻辑 登录失败返回信息 以此为依据跳转
     *
     * @return the authentication failure handler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailedHandler();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage) {
        return new JwtAuthenticationFilter(jwtTokenGenerator, jwtTokenStorage);
    }
}
