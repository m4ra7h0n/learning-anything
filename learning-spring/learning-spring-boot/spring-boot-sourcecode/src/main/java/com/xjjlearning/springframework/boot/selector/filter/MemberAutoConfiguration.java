package com.xjjlearning.springframework.boot.selector.filter;

import com.xjjlearning.springframework.boot.selector.classes.MemberRegisterService;
import org.springframework.context.annotation.Bean;

/**
 * 不写configuration也能自动装配, 请看spring.factories
 * 核心在于  AutoConfigurationImportSelector
 */
// @Configuration
public class MemberAutoConfiguration {

    @Bean
    public MemberRegisterService registerService() {
        return new MemberRegisterService();
    }
}