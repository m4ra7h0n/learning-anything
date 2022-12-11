package com.xjjlearning.springframework.security.filter;

import com.xjjlearning.springframework.security.enumation.LoginTypeEnum;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

/**
 * 预登录控制器
 *
 * @author Felordcn
 * @since 16 :21 2019/10/17
 */
public class PreLoginFilter extends GenericFilterBean {

    private static final String LOGIN_TYPE_KEY = "login_type";

    private RequestMatcher requiresAuthenticationRequestMatcher;

    private Map<LoginTypeEnum, LoginPostProcessor> processors = new HashMap<>();

    public PreLoginFilter(String loginProcessingUrl, Collection<LoginPostProcessor> loginPostProcessors) {

        Assert.notNull(loginProcessingUrl, "loginProcessingUrl must not be null");
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(loginProcessingUrl, "POST");
        // 应该加载所有的类型到这个map中
        LoginPostProcessor loginPostProcessor = defaultLoginPostProcessor();
        processors.put(loginPostProcessor.getLoginTypeEnum(), loginPostProcessor);

        if (!CollectionUtils.isEmpty(loginPostProcessors)) {
            loginPostProcessors.forEach(element -> processors.put(element.getLoginTypeEnum(), element));
        }

    }

    private LoginTypeEnum getTypeFromReq(ServletRequest request) {
        String parameter = request.getParameter(LOGIN_TYPE_KEY);

        int i = Integer.parseInt(parameter);
        LoginTypeEnum[] values = LoginTypeEnum.values();
        return values[i];
    }

    /**
     * 默认还是Form .
     *
     * @return the login post processor
     */
    private LoginPostProcessor defaultLoginPostProcessor() {
        return new FormLoginPostProcessor();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);

        if (requiresAuthenticationRequestMatcher.matches((HttpServletRequest) request)) {

            LoginTypeEnum typeFromReq = getTypeFromReq(request);

            LoginPostProcessor loginPostProcessor = processors.get(typeFromReq);

            String username = loginPostProcessor.obtainUsername(request);

            String password = loginPostProcessor.obtainPassword(request);

            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, username);
            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, password);
        }

        chain.doFilter(parameterRequestWrapper, response);
    }
}
