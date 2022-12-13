package com.xjjlearning.springframework.security.service;


import com.xjjlearning.springframework.security.entity.MetaResource;

import java.util.Set;

/**
 *  获取可用的资源的资源pattern 和 method 用以构建 AntPathRequestMatcher
 *
 * @author Felordcn
 * @since 15 :11 2019/11/28
 */
public interface MetaResourceService {


    /**
     * 获取对应uri 的ant  pattern  和请求方法.
     *
     * @return the map
     */
    Set<MetaResource> queryPatternsAndMethods();


}
