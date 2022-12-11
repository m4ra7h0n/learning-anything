package com.xjjlearning.springframework.security.filter;

import com.xjjlearning.springframework.security.enumation.LoginTypeEnum;

import javax.servlet.ServletRequest;

/**
 * The interface Login post processor.
 *
 * @author dax
 * @since 2019 /10/17 20:49
 */
public interface LoginPostProcessor {

    /**
     * 获取 登录类型
     *
     * @return the type
     */
    LoginTypeEnum getLoginTypeEnum();

    /**
     * 获取用户名
     *
     * @param request the request
     * @return the string
     */
    String obtainUsername(ServletRequest request);

    /**
     * 获取密码
     *
     * @param request the request
     * @return the string
     */
    String obtainPassword(ServletRequest request);

}
