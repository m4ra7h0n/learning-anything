package com.xjjlearning.springframework.security.service.impl;

import com.xjjlearning.springframework.security.dao.SysUserDAO;
import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.SysUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * created by xjj on 2022/12/13
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserDAO sysUserDAO;

    @Override
    public SysUser findByUsername(String username) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username);
        return sysUserDAO.selectOneByExample(example);
    }
}
