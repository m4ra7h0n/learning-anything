package com.xjjlearning.alibaba.cloud.seata.stock.dubbo;

import com.xjjlearning.alibaba.cloud.seata.common.dto.CommodityDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.StockDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import com.xjjlearning.alibaba.cloud.seata.stock.service.TStockServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class StockDubboServiceImpl implements StockDubboService {
    @Resource
    TStockServiceImpl tStockService;

    @Override
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        return tStockService.decreaseStock(commodityDTO);
    }
}
