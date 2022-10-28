package com.xjjlearning.alibaba.druid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.xjjlearning.alibaba.druid.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
