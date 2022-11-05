package com.xjjlearning.alibaba.cloud.seata.order.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.OrderDTO;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;

public interface ITOrderService {
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
