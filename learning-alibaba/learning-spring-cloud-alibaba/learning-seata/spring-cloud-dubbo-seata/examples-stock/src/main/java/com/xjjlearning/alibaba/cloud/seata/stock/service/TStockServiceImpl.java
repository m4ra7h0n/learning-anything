package com.xjjlearning.alibaba.cloud.seata.stock.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.CommodityDTO;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.stock.mapper.TStockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TStockServiceImpl implements ITStockService{
    @Resource
    TStockMapper tStockMapper;

    @Override
    @Transactional
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        ObjectResponse objectResponse = new ObjectResponse();
        int count = tStockMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        if (count > 0) {
            objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
            objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return objectResponse;
        }
        objectResponse.setStatus(RspStatusEnum.FAIL.getCode());
        objectResponse.setMessage(RspStatusEnum.FAIL.getMessage());
        return objectResponse;
    }
}
