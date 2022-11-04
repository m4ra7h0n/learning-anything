package com.xjjlearning.alibaba.cloud.seata.order.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dto.OrderDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.AccountDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.order.mapper.TOrderMapper;
import com.xjjlearning.alibaba.cloud.seata.order.model.TOrder;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class TOrderServiceImpl implements ITOrderService{
    @Resource
    TOrderMapper tOrderMapper;
    @DubboReference
    AccountDubboService accountDubboService;

    @Override
    @Transactional
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        System.out.println(RootContext.getXID());
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        // 先减扣账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        ObjectResponse objectResponse = accountDubboService.decreaseAccount(accountDTO);

        // 生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        // 生成订单
        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        tOrder.setCount(orderDTO.getOrderCount());

        // 更新
        tOrderMapper.createOrder(tOrder);

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
