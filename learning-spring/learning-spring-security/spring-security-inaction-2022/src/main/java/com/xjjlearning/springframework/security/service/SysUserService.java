package com.xjjlearning.springframework.security.service;

import com.xjjlearning.springframework.security.entity.SysUser;

/**
 * created by xjj on 2022/12/13
 */
public interface SysUserService {
    SysUser findByUsername(String username);
}
