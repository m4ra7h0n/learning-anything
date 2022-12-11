package com.xjjlearning.springframework.security.filter;


import com.xjjlearning.springframework.security.enumation.LoginTypeEnum;

import javax.servlet.ServletRequest;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

/**
 * created by xjj on 2022/12/11
 */
public class FormLoginPostProcessor implements LoginPostProcessor{
    @Override
    public LoginTypeEnum getLoginTypeEnum() {
        return LoginTypeEnum.FORM;
    }

    @Override
    public String obtainUsername(ServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    }

    @Override
    public String obtainPassword(ServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
    }
}
