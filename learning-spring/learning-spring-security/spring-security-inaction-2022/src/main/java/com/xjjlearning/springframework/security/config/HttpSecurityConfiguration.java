package com.xjjlearning.springframework.security.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * created by xjj on 2022/12/13
 */
// @Configuration
public class HttpSecurityConfiguration {

    private static final String HTTP_SECURITY_BEAN_NAME = "http_security_bean";

    private AuthenticationManagerBuilder authenticationBuilder;

    @Bean(HTTP_SECURITY_BEAN_NAME)
    @Scope("prototype")
    HttpSecurity httpSecurity(ApplicationContext context) throws Exception {

        Map<Class<?>, Object> sharedObjects = createSharedObjects();

        ObjectPostProcessor<Object> objectPostProcessor = context.getBean(ObjectPostProcessor.class);

        // WebSecurityConfigurerAdapter.LazyPasswordEncoder passwordEncoder = new WebSecurityConfigurerAdapter.LazyPasswordEncoder(context);

        // authenticationBuilder = new WebSecurityConfigurerAdapter.DefaultPasswordEncoderAuthenticationManagerBuilder(objectPostProcessor, passwordEncoder);

        HttpSecurity http = new HttpSecurity(this.objectPostProcessor, authenticationBuilder, createSharedObjects());
        http
                .csrf(withDefaults())
                .addFilter(new WebAsyncManagerIntegrationFilter())
                .exceptionHandling(withDefaults())
                .headers(withDefaults())
                .sessionManagement(withDefaults())
                .securityContext(withDefaults())
                .requestCache(withDefaults())
                .anonymous(withDefaults())
                .servletApi(withDefaults())
                .apply(new DefaultLoginPageConfigurer<>());

        http.logout(withDefaults());

        return http;
    }

    private Map<Class<?>, Object> createSharedObjects() {
        Map<Class<?>, Object> sharedObjects = new HashMap<>();
        return sharedObjects;
    }

    private ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<Object>() {
        public <T> T postProcess(T object) {
            throw new IllegalStateException(
                    ObjectPostProcessor.class.getName()
                            + " is a required bean. Ensure you have used @EnableWebSecurity and @Configuration");
        }
    };

}
