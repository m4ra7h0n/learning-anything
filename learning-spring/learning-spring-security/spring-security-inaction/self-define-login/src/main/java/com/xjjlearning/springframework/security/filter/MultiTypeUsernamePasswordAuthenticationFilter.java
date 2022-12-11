package com.xjjlearning.springframework.security.filter;

import com.xjjlearning.springframework.security.enumation.LoginTypeEnum;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * created by xjj on 2022/12/11
 */
public class MultiTypeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String LOGIN_TYPE_KEY = "login_type";

    private RequestMatcher requiresAuthenticationRequestMatcher;

    private LoginPostProcessor defaultLoginPostProcessor() {
        return new FormLoginPostProcessor();
    }

    public MultiTypeUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private Map<LoginTypeEnum, LoginPostProcessor> processors = new HashMap<>();

    public MultiTypeUsernamePasswordAuthenticationFilter(String loginProcessingUrl, Collection<LoginPostProcessor> loginPostProcessors) {
        Assert.notNull(loginProcessingUrl, "loginProcessingUrl must not be null");
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(loginProcessingUrl, "POST");
        // 应该加载所有的类型到这个map中
        LoginPostProcessor loginPostProcessor = defaultLoginPostProcessor();
        processors.put(loginPostProcessor.getLoginTypeEnum(), loginPostProcessor);

        if (!CollectionUtils.isEmpty(loginPostProcessors)) {
            loginPostProcessors.forEach(element -> processors.put(element.getLoginTypeEnum(), element));
        }
    }

    public MultiTypeUsernamePasswordAuthenticationFilter() {
        super();
    }

    private LoginTypeEnum getTypeFromReq(ServletRequest request) {
        String parameter = request.getParameter(LOGIN_TYPE_KEY);

        int i = Integer.parseInt(parameter);
        LoginTypeEnum[] values = LoginTypeEnum.values();
        return values[i];
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        LoginTypeEnum typeFromReq = getTypeFromReq(request);
        LoginPostProcessor loginPostProcessor = processors.get(typeFromReq);
        return loginPostProcessor.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        LoginTypeEnum typeFromReq = getTypeFromReq(request);
        LoginPostProcessor loginPostProcessor = processors.get(typeFromReq);
        return loginPostProcessor.obtainUsername(request);
    }
}
