package com.xjjlearning.springframework.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author dax
 * @since 2019/10/17 22:09
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {


    public ParameterRequestWrapper(HttpServletRequest request ) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
       return (String) super.getAttribute(name);
    }
}
