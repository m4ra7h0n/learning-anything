package com.xjjlearning.alibaba.cloud.seata.order.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dto.OrderDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.AccountDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.order.mapper.TOrderMapper;
import com.xjjlearning.alibaba.cloud.seata.order.model.TOrder;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        // 先减扣账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        ObjectResponse objectResponse = accountDubboService.decreaseAccount(accountDTO);

        if (objectResponse.getStatus() != 200) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        // 生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        // 生成订单
        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        tOrder.setCount(orderDTO.getOrderCount());

        try {
            tOrderMapper.createOrder(tOrder);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
