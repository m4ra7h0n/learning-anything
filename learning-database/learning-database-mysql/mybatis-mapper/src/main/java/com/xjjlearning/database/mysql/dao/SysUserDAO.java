package com.xjjlearning.database.mysql.dao;

import com.xjjlearning.database.mysql.entity.SysUser;
import io.mybatis.mapper.Mapper;

/**
 * created by xjj on 2022/12/11
 */
@org.apache.ibatis.annotations.Mapper
public interface SysUserDAO extends Mapper<SysUser, Long> {}
