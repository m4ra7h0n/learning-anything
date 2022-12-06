package com.xjjlearning.springframework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * created by xjj on 2022/12/5
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // one -> each method returns HttpSecurity.this
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .httpBasic(withDefaults())
                // BasicAuthenticationFilter
                .formLogin(withDefaults());

        // two -> traditional
        // http.authorizeHttpRequests()
        //         .anyRequest().authenticated()
        //         .and()
        //         .formLogin()
        //         .and()
        //         .httpBasic();

        return http.build();


    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // UserDetails(interface) -> User
        // needs passwordEncoder
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        // UserDetails admin = User.builder()
        //         .username("user")
        //         .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
        //         .roles("USER", "ADMIN")
        //         .build();
        // DaoAuthenticationProvider
        // UsernamePasswordAuthenticationFilter
        return new InMemoryUserDetailsManager(user);
    }

    // @Bean
    // public JdbcUserDetailsManager jdbcUserDetailsManager() {
    //     // UserDetailsServiceAutoConfiguration
    //     return new JdbcUserDetailsManager();
    // }
}
