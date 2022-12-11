package com.xjjlearning.springframework.security.component;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * created by xjj on 2022/12/9
 */
@Component
public class PasswordEncoderConfig {
    @Bean
    PasswordEncoder delegatingPasswordEncoder() { // delegate encode
        // Provides a convenient base class for creating a WebSecurityConfigurer instance.
        // The implementation allows customization by overriding methods.

        // how spring load PasswordEncoder ?
        // WebSecurityConfigurerAdapter
        // LazyPasswordEncoder
        // getPasswordEncoder()
        // setApplicationContext() use the LazyPasswordEncoder

        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    PasswordEncoder passwordEncoderFactories() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
