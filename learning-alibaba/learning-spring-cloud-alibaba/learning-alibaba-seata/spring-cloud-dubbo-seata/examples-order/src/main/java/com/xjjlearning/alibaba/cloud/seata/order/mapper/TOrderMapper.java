package com.xjjlearning.alibaba.cloud.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.order.model.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {
    /**
     * 创建订单
     * @param order
     */
    @Insert({"insert into t_order (order_no, user_id, commodity_code, count, amount) values (#{orderNo}, #{userId}, #{commodityCode}, #{count}, #{amount})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createOrder(TOrder order);
}
