package com.xjjlearning.springframework.security.config;

/**
 * created by xjj on 2022/12/10
 */
public class AutoConfiguration {
    public static void main(String[] args) {
        // 核心配置类1
        // SecurityAutoConfiguration

        // 1.SpringBootWebSecurityConfiguration
        //   - 自定义WebSecurityConfigurerAdapter 将取消该类配置
        // 2.WebSecurityEnablerConfiguration
        //   - EnableWebSecurity
        //      启用 EnableGlobalAuthentication
        //          为了导入配置: AuthenticationConfiguration
        //            1.构造AuthenticationManager
        //      导入 WebSecurityConfiguration
        //          1. 使用一个 WebSecurity 对象基于安全配置创建一个 FilterChainProxy 对象来对用户请求 进行安全过滤
        //              -> springSecurityFilterChain() spring生命周期 过度到 security的生命周期 遍历所有 securityFilterChain 的所有 filters
        //              -> setFilterChainProxySecurityConfigurer() 先按照 order 排序 webSecurityConfigurer 然后依次调用apply方法
        //          2. 也会暴露诸如 安全SpEL表达式处理器 SecurityExpressionHandler 等一些类
        //      导入 SpringWebMvcImportSelector
        //          1.适配mvc
        //      导入 OAuth2ImportSelector
        //          1.适配oauth2
        //      导入 HttpSecurityConfiguration
        //          .
        // 3.SecurityDataConfiguration.class
        // 4.ErrorPageSecurityFilterConfiguration


        // 核心配置类2
        // SecurityFilterAutoConfiguration
        // EnableConfigurationProperties
        //      加载 spring.security 开头的配置
        // @AutoConfigureAfter(SecurityAutoConfiguration.class)
        //      核心类1加载完后加载此类
        // 注册 DelegatingFilterProxyRegistrationBean


        /**
             1.SpringBootWebSecurityConfiguration
                其中的默认加载策略 DefaultWebSecurityCondition 不太理解下面的
                bean 和 class是不一样的
        */
        // @ConditionalOnClass({ SecurityFilterChain.class, HttpSecurity.class })
        // static class Classes {
        //
        // }
        //
        // @ConditionalOnMissingBean({ WebSecurityConfigurerAdapter.class, SecurityFilterChain.class })
        // static class Beans {
        //
        // }



        // 2.WebSecurityEnablerConfiguration
        // 目的仅仅就是在某些
        // 条件下激活 @EnableWebSecurity 注解
    }
}
