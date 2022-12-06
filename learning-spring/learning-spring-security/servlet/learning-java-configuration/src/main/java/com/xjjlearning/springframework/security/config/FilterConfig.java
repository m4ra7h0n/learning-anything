package com.xjjlearning.springframework.security.config;

import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static javax.servlet.DispatcherType.*;

@Configuration
public class FilterConfig {
    /**
     * 过滤器代理注册器
     *
     * DelegatingFilterProxyRegistrationBean 通过传入的proxyFilter名字,在WebApplicationContext查找该Fillter Bean,
     * 并通过DelegatingFilterProxy生成基于该Bean的代理Filter对象
     */
    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        DelegatingFilterProxyRegistrationBean registration = new DelegatingFilterProxyRegistrationBean("myFilter");
        registration.setOrder(100);
        registration.addUrlPatterns("/*");
        registration.setDispatcherTypes(ASYNC, ERROR, FORWARD, INCLUDE, REQUEST);
        return registration;
    }
}