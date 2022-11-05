package com.xjjlearning.alibaba.cloud.seata.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjjlearning.alibaba.cloud.seata.entity.Stock;
import com.xjjlearning.alibaba.cloud.seata.repository.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StockService {
    @Resource
    StockDAO stockDAO;

    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockDAO.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockDAO.updateById(stock);
    }
}
