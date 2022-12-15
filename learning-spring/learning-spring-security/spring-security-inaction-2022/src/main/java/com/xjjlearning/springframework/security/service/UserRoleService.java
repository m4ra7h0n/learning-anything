package com.xjjlearning.springframework.security.service;

import com.xjjlearning.springframework.security.entity.Role;

import java.util.List;

/**
 * created by xjj on 2022/12/13
 */
public interface UserRoleService {
    List<Role> findRolesByUsername(String username);
}
