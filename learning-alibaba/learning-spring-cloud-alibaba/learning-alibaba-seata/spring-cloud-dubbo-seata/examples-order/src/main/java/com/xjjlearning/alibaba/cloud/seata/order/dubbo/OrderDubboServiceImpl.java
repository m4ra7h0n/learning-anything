package com.xjjlearning.alibaba.cloud.seata.order.dubbo;

import com.xjjlearning.alibaba.cloud.seata.common.dto.OrderDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.OrderDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.order.service.TOrderServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class OrderDubboServiceImpl implements OrderDubboService {
    @Resource
    TOrderServiceImpl tOrderService;
    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        return tOrderService.createOrder(orderDTO);
    }
}
