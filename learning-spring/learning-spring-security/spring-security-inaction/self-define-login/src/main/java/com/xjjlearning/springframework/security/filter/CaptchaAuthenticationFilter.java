package com.xjjlearning.springframework.security.filter;

import com.xjjlearning.springframework.security.authentication.CaptchaAuthenticationToken;
import io.micrometer.core.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模仿 UsernamePasswordAuthenticationFilter
 */
public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";


    public CaptchaAuthenticationFilter() {
        super(new AntPathRequestMatcher("/clogin", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String phone = obtainPhone(request);
        String captcha = obtainCaptcha(request);

        if (phone == null) {
            phone = "";
        }

        if (captcha == null) {
            captcha = "";
        }

        phone = phone.trim();

        CaptchaAuthenticationToken authRequest = new CaptchaAuthenticationToken(
                phone, captcha);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Nullable
    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_CAPTCHA_KEY);
    }

    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_PHONE_KEY);
    }

    protected void setDetails(HttpServletRequest request,
                              CaptchaAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}