package com.xjjlearning.alibaba.cloud.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.order.model.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {
    /**
     * 创建订单
     * @param order
     */
    void createOrder(TOrder order);
}
