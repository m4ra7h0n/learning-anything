package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.UserDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * The type User details service configuration.
 *
 * @author dax
 */
@Configuration
public class UserDetailsServiceConfiguration {

    /**
     * UserDetails 持久化出口.
     *
     * @return the user details repository
     */
    @Bean
    public UserDetailsRepository userDetailsRepository() {
        return new UserDetailsRepository();
    }


    /**
     * User details manager 自定义.
     *
     * @param userDetailsRepository the user details repository
     * @see org.springframework.security.provisioning.JdbcUserDetailsManager
     * @return the user details manager
     */
    @Bean
    public UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository) {
        return new UserDetailsManager() {
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

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetailsRepository.loadUserByUsername(username);
            }
        };
    }

}
