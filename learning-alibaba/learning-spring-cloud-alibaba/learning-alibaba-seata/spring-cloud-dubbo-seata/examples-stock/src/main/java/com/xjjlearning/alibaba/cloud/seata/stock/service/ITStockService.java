package com.xjjlearning.alibaba.cloud.seata.stock.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.CommodityDTO;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;

public interface ITStockService {
    ObjectResponse decreaseStock(CommodityDTO commodityDTO);
}
