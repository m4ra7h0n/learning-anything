package com.xjjlearning.springframework.security.dynamic;

import com.xjjlearning.springframework.security.entity.MetaResource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Set;

/**
 * RequestMatcherRepository
 *
 * @author Felordcn
 * @since 14 :56 2019/11/28
 */
@FunctionalInterface
public interface RequestMatcherCreator {

    /**
     * 转换为 reqMatcher
     *
     * @param metaResources metaResource
     * @return  reqMatcher
     */
    Set<RequestMatcher> convertToRequestMatcher(Set<MetaResource> metaResources);


}
