package com.xjjlearning.springframework.security.config;

/**
 * created by xjj on 2022/12/5
 */
// @EnableWebSecurity
// public class WebSecurityConfig implements WebMvcConfigurer {
//     @Bean
//     public UserDetailsService userDetailsService() throws Exception {
//         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         if (principal instanceof UserDetails) {
//             System.out.println("hello world");
//             System.out.println("user name: " + ((UserPrincipal) principal).getName());
//         }
//
//         InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//         manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//         return manager;
//     }
// }
