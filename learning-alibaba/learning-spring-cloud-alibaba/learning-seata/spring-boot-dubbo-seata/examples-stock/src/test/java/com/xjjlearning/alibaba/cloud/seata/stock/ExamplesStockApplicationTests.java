package com.xjjlearning.alibaba.cloud.seata.stock;

import com.xjjlearning.alibaba.cloud.seata.stock.mapper.TStockMapper;
import com.xjjlearning.alibaba.cloud.seata.stock.model.TStock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExamplesStockApplicationTests {
    @Resource
    TStockMapper tStockMapper;

    @Test
    void mybatisTest() {
        TStock tStock = tStockMapper.selectById(1L);
        System.out.println(tStock);
    }
}
