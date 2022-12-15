package com.xjjlearning.springframework.security.dao;

import com.xjjlearning.springframework.security.entity.UserRole;
import tk.mybatis.mapper.common.Mapper;

/**
 * created by xjj on 2022/12/13
 */
@org.apache.ibatis.annotations.Mapper
public interface UserRoleDAO extends Mapper<UserRole> {
}
