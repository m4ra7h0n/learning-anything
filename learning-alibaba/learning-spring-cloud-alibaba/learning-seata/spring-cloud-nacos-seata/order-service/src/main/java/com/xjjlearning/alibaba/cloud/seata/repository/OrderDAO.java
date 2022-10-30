package com.xjjlearning.alibaba.cloud.seata.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDAO extends BaseMapper<Order> {
}
