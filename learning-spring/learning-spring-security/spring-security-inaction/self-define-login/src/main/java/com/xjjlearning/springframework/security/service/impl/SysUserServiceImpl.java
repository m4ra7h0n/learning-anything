package com.xjjlearning.springframework.security.service.impl;

import com.xjjlearning.springframework.security.dao.SysUserDAO;
import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.SysUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * created by xjj on 2022/12/11
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserDAO sysUserDAO;

    @Override
    public SysUser queryByUsername(String username) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username);
        return sysUserDAO.selectOneByExample(example);
        // Example<SysUser> example = new Example<>();
        // example.createCriteria().andEqualTo(SysUser::getUsername, username);
        // return sysUserDAO.selectOneByExample(example).orElse(new SysUser());
    }

    @Override
    public Integer addUser(SysUser sysUser) {
        return sysUserDAO.insert(sysUser);
    }

    @Override
    public Integer updateUser(SysUser sysUser) {
        return sysUserDAO.updateByPrimaryKey(sysUser);
    }

    @Override
    public Integer removeUser(SysUser sysUser) {
        return sysUserDAO.delete(sysUser);
    }
}
