package com.xjjlearning.springframework.security.dao;

import com.xjjlearning.springframework.security.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

/**
 * created by xjj on 2022/12/11
 */
@org.apache.ibatis.annotations.Mapper
public interface SysUserDAO extends Mapper<SysUser> {}
