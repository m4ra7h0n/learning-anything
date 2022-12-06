package com.xjjlearning.springframework.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Oauth2ResourceServerApplicationTests {

    @Test
    void contextLoads() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        System.out.println(user.getPassword());

        UserDetails admin = User.withUsername("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        System.out.println(admin);

        // strength->16, needs time to decode
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        String password = bCryptPasswordEncoder.encode("password");
        assertTrue(bCryptPasswordEncoder.matches("password", password));

        // the same as
        // Argon2PasswordEncoder
        // PBKDF2PASSWORDENCODER
        // SCryptPasswordEncoder
        // and so on

    }

}
