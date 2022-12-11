package com.xjjlearning.database.mysql.service.impl;

import com.xjjlearning.database.mysql.dao.SysUserDAO;
import com.xjjlearning.database.mysql.entity.SysUser;
import com.xjjlearning.database.mysql.service.SysUserService;
import io.mybatis.mapper.example.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by xjj on 2022/12/11
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserDAO sysUserDAO;

    @Override
    public void select(String username) {
        Example<SysUser> example = new Example<>();
        example.createCriteria().andEqualTo(SysUser::getUsername, username);
        SysUser sysUser = sysUserDAO.selectOneByExample(example).orElse(new SysUser());
        System.out.println(sysUser);
    }
}
