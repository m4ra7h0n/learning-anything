package com.xjjlearning.springframework.security.dynamic;

import com.xjjlearning.springframework.security.service.MetaResourceService;
import com.xjjlearning.springframework.security.service.RoleService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

/**
 * 参考 ExpressionBasedFilterInvocationSecurityMetadataSource
 *
 **/

public class DynamicFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private RequestMatcherCreator requestMatcherCreator;
    @Resource
    private MetaResourceService metaResourceService;
    @Resource
    private RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 获取当前的请求
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        // 这里可以放一个抽象接口来获取  request 配置的 ant pattern
        Set<RequestMatcher> requestMatchers = requestMatcherCreator.convertToRequestMatcher(metaResourceService.queryPatternsAndMethods());
        // 拿到其中一个  没有就算非法访问
        RequestMatcher reqMatcher = requestMatchers.stream()
                .filter(requestMatcher -> requestMatcher.matches(request))
                .findAny()
                .orElseThrow(() -> new AccessDeniedException("非法访问"));

        AntPathRequestMatcher antPathRequestMatcher = (AntPathRequestMatcher) reqMatcher;
        // 根据pattern 获取该pattern被授权给的角色
        String pattern = antPathRequestMatcher.getPattern();
        Set<String> roles = roleService.queryRoleByPattern(pattern);

        return CollectionUtils.isEmpty(roles) ? null : SecurityConfig.createList(roles.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<String> roles = roleService.queryAllAvailable();
        return CollectionUtils.isEmpty(roles) ? null : SecurityConfig.createList(roles.toArray(new String[0]));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
