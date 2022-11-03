package com.xjjlearning.alibaba.cloud.seata.business.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.BusinessDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dto.CommodityDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dto.OrderDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.OrderDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.StockDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.exception.DefaultException;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService{
    @DubboReference
    StockDubboService stockDubboService;
    @DubboReference
    OrderDubboService orderDubboService;

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        System.out.println("开始全局事务, XID = " + RootContext.getXID());
        ObjectResponse objectResponse = new ObjectResponse();

        // 1.扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        ObjectResponse stockResp = stockDubboService.decreaseStock(commodityDTO);

        // 2.创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderAmount(businessDTO.getAmount());
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        ObjectResponse<OrderDTO> orderResp = orderDubboService.createOrder(orderDTO);

        // 3.发生错误回滚
        if (orderResp.getStatus() != 200 || stockResp.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        // 4.返回消息
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setData(orderResp.getData());
        return objectResponse;
    }
}
