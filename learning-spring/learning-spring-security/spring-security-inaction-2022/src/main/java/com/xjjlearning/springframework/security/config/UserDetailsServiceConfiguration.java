package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.entity.Role;
import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.SysUserService;
import com.xjjlearning.springframework.security.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by xjj on 2022/12/13
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity//(debug = true)
public class UserDetailsServiceConfiguration {
    @Resource
    SysUserService sysUserService;
    @Resource
    UserRoleService userRoleService;

    // @Bean
    // public UserDetails userDetails() {
    //     User.UserBuilder builder = User.withUsername("xjj")
    //             .password("{noop}12345")
    //             .authorities("ROLE_ADMIN", "ROLE_USER");
    //     return builder.build();
    // }
    @Bean
    public UserDetailsService jdbcUserDetailsService() {
        return username -> {
            SysUser sysUser = sysUserService.findByUsername(username);
            if (sysUser == null) {
                throw new UsernameNotFoundException("user not found");
            }
            List<Role> roles = userRoleService.findRolesByUsername(username);
            if (roles == null || roles.isEmpty()) {
                throw new UsernameNotFoundException("no authority");
            }

            return User.withUsername(username)
                    .password(sysUser.getEncodePassword())
                    .authorities(roles.stream().map(Role::getRoleName).toArray(String[]::new))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
