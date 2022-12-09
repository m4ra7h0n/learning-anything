package com.xjjlearning.database.mysql.service;

import com.xjjlearning.database.mysql.model.SysRole;

public interface RoleService {
    SysRole selectRole(Long id);
}
