package com.xjjlearning.springframework.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * created by xjj on 2022/12/10
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSpringBootWebSecurityConfiguration {
    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    static class DefaultConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("xjj").password("{noop}1234").roles("USER");
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            // WebSecurityConfiguration 掌管该逻辑
            super.configure(web);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/index.html", "/static/**").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin()
                    .and().httpBasic();
            super.configure(http);
        }
    }
}

// @Configuration(proxyBeanMethods = false)
// @ConditionalOnDefaultWebSecurity
// @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
// class SpringBootWebSecurityConfiguration {
//
//     @Bean
//     @Order(SecurityProperties.BASIC_AUTH_ORDER)
//     SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//         http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//         return http.build();
//     }
//
// }