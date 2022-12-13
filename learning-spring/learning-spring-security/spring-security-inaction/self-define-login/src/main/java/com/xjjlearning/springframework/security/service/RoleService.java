package com.xjjlearning.springframework.security.service;

import java.util.Set;

/**
 * RoleService
 *
 * @author Felordcn
 * @since 15 :35 2019/11/28
 */
public interface RoleService {

    /**
     * 根据路径pattern 来获取 对应的角色.
     *
     * @param pattern the pattern
     * @return the set
     */
    Set<String>  queryRoleByPattern(String pattern);

    /**
     * 获取所有可用的
     *
     * @return
     */
    Set<String>  queryAllAvailable();
}
