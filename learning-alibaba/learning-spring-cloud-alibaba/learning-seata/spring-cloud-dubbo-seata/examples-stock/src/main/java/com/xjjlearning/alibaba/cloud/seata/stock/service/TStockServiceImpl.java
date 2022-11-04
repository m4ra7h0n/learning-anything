package com.xjjlearning.alibaba.cloud.seata.stock.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xjjlearning.alibaba.cloud.seata.common.dto.CommodityDTO;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.stock.mapper.TStockMapper;
import com.xjjlearning.alibaba.cloud.seata.stock.model.TStock;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TStockServiceImpl implements ITStockService{
    @Resource
    TStockMapper tStockMapper;

    @Override
    @Transactional
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        System.out.println(RootContext.getXID());
        ObjectResponse objectResponse = new ObjectResponse();
        Integer count = commodityDTO.getCount();
        if (count < 0) {
            throw new RuntimeException("[StockError]: Try to buy more than one goods?");
        }

        TStock tStock = tStockMapper.selectOne(Wrappers.<TStock>lambdaQuery()
                .eq(TStock::getCommodityCode, commodityDTO.getCommodityCode()));

        if (tStock == null) {
            throw new RuntimeException("[StockError]: No such goods");
        }

        if (tStock.getCount() - count < 0) {
            throw new RuntimeException("[StockError]: No more Stock");
        }

        // 更新
        tStock.setCount(tStock.getCount() - count);
        tStockMapper.updateById(tStock);

        objectResponse.setStatus(RspStatusEnum.FAIL.getCode());
        objectResponse.setMessage(RspStatusEnum.FAIL.getMessage());
        return objectResponse;
    }
}
