package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.repository.UserDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * created by xjj on 2022/12/9
 */
@Configuration
public class UserDetailsConfiguration {  // implements UserDetailsService {
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // implement the default user, UserDetailsManager
    //     UserDetailsServiceAutoConfiguration configuration = new UserDetailsServiceAutoConfiguration();
    //     User.UserBuilder builder = User.builder()
    //             .username("xjj")
    //             .password("{noop}12345")
    //             .roles("ROLE_ADMIN");
    //     return builder.build();
    // }


    /**
     * 实现方式1, 系统自带的UserDetailsManager -> InMemory
     * @return
     */
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails xjj = User.withUsername("xjj")
                .password("{noop}1234")
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(xjj);
    }


    /**
     * 实现方式2, 自己实现UserDetailsManager
     * @return
     */
    @Bean
    UserDetailsRepository userDetailsRepository() {
        UserDetailsRepository userDetailsRepository = new UserDetailsRepository();
        UserDetails xjj = User
                .withUsername("xjj")
                .password("{noop}1234")
                .authorities(AuthorityUtils.NO_AUTHORITIES).build();
        userDetailsRepository.createUser(xjj);
        return userDetailsRepository;
    }

    // Why UserDetailsServiceAutoConfiguration can be substituted ?
    @Bean
    UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository) {
        return new UserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetailsRepository.loadUserByUsername(username);
            }

            @Override
            public void createUser(UserDetails user) {
                userDetailsRepository.createUser(user);
            }

            @Override
            public void updateUser(UserDetails user) {

                userDetailsRepository.updateUser(user);
            }

            @Override
            public void deleteUser(String username) {
                userDetailsRepository.deleteUser(username);
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                userDetailsRepository.changePassword(oldPassword, newPassword);
            }

            @Override
            public boolean userExists(String username) {
                return userDetailsRepository.userExists(username);
            }
        };
    }
}
