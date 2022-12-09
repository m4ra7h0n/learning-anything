package com.xjjlearning.database.mysql.service.impl;

import com.xjjlearning.database.mysql.mapper.RoleMapper;
import com.xjjlearning.database.mysql.model.SysRole;
import com.xjjlearning.database.mysql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public SysRole selectRole(Long id) {
        return roleMapper.selectById(id);
    }
}
