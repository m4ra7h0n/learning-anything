package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.dynamic.DynamicFilterInvocationSecurityMetadataSource;
import com.xjjlearning.springframework.security.dynamic.RequestMatcherCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态权限组件配置
 */
@Configuration
public class DynamicAccessControlConfiguration {
    /**
     * RequestMatcher 生成器
     *
     * @return RequestMatcher
     */
    @Bean
    public RequestMatcherCreator requestMatcherCreator() {
        return metaResources -> metaResources.stream()
                .map(metaResource -> new AntPathRequestMatcher(metaResource.getPattern(), metaResource.getMethod()))
                .collect(Collectors.toSet());
    }

    /**
     * 元数据加载器
     *
     * @return dynamicFilterInvocationSecurityMetadataSource
     */
    @Bean
    public FilterInvocationSecurityMetadataSource dynamicFilterInvocationSecurityMetadataSource() {
        return new DynamicFilterInvocationSecurityMetadataSource();
    }

    /**
     * 角色投票器
     *
     * @return roleVoter
     */
    @Bean
    public RoleVoter roleVoter() {
        return new RoleVoter();
    }

    /**
     * 基于肯定的访问决策器
     *

       AffirmativeBased 基于肯定的决策器。 用户持有一个同意访问的角色就能通过。
       ConsensusBased 基于共识的决策器。 用户持有同意的角色数量多于禁止的角色数。
       UnanimousBased 基于一致的决策器。 用户持有的所有角色都同意访问才能放行。

     * @param decisionVoters AccessDecisionVoter类型的 Bean 会自动注入到 decisionVoters
     * @return affirmativeBased
     */
    @Bean
    public AccessDecisionManager affirmativeBased(List<AccessDecisionVoter<?>> decisionVoters) {
        return new AffirmativeBased(decisionVoters);
    }

}
