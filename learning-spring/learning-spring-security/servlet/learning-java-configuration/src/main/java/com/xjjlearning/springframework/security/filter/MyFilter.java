package com.xjjlearning.springframework.security.filter;

import jakarta.servlet.*;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component("myFilter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    // before reach servlet
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        // user
        Object principal = authentication.getPrincipal();
        // role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // password
        Object credentials = authentication.getCredentials();

        /**
         * 1
         the entrance of all the authenticates such as user/pass email/pass etc.
         A DisabledException must be thrown if an account is disabled and the AuthenticationManager can test for this state.
         A LockedException must be thrown if an account is locked and the AuthenticationManager can test for account locking.
         A BadCredentialsException must be thrown if incorrect credentials are presented. Whilst the above exceptions are optional, an AuthenticationManager must always test credentials.
         */
        // ProviderManager implement AuthenticationManager(interface)
        ProviderManager providerManager = new ProviderManager();

        /**
         * 2
         in the ProviderManager AuthenticationProvider provides the authentication. such as jwt
         */
        // JwtAuthenticationProvider implement AuthenticationProvider(interface)

        /**
         * 3 i don't know what's it
         */
        // AuthenticationEntryPoint(interface)
        // BearerTokenAuthenticationEntryPoint implement AuthenticationEntryPoint


        // AbstractAuthenticationProcessingFilter
        // UsernamePasswordAuthenticationFilter implement above
        // OAuth2LoginAuthenticationFilter implement above
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();


        System.out.println("doFilter start");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter end");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}