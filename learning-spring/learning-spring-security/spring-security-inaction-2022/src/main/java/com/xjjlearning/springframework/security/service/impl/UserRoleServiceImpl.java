package com.xjjlearning.springframework.security.service.impl;

import com.xjjlearning.springframework.security.dao.RoleDAO;
import com.xjjlearning.springframework.security.dao.UserRoleDAO;
import com.xjjlearning.springframework.security.entity.Role;
import com.xjjlearning.springframework.security.entity.UserRole;
import com.xjjlearning.springframework.security.service.UserRoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by xjj on 2022/12/13
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    UserRoleDAO userRoleDAO;
    @Resource
    RoleDAO roleDAO;

    @Override
    public List<Role> findRolesByUsername(String username) {
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("username", username);
        List<UserRole> userRoles = userRoleDAO.selectByExample(example);

        return userRoles.stream()
                .map(UserRole::getRoleId)
                .map(id -> roleDAO.selectByPrimaryKey(id))
                .collect(Collectors.toList());
    }
}
