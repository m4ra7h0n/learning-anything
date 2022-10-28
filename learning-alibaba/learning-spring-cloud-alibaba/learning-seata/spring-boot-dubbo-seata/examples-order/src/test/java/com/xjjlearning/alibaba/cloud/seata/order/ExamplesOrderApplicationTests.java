package com.xjjlearning.alibaba.cloud.seata.order;

import com.xjjlearning.alibaba.cloud.seata.order.mapper.TOrderMapper;
import com.xjjlearning.alibaba.cloud.seata.order.model.TOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExamplesOrderApplicationTests {
    @Resource
    TOrderMapper tOrderMapper;

    @Test
    void mybatisTest() {
        TOrder tOrder = tOrderMapper.selectById(1L);
        System.out.println(tOrder);
    }
}
