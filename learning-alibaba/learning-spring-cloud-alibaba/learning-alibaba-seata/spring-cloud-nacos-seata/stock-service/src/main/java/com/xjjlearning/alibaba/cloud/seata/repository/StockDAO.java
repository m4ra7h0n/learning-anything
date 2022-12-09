package com.xjjlearning.alibaba.cloud.seata.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockDAO extends BaseMapper<Stock> {
}
