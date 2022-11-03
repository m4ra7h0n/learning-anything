package com.xjjlearning.alibaba.cloud.seata.account;

import com.xjjlearning.alibaba.cloud.seata.account.mapper.TAccountMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExamplesAccountApplicationTests {
    @Resource
    TAccountMapper tAccountMapper;

    @Test
    void mybatisTest() {
        TAccount tAccount = tAccountMapper.selectById(1L);
        System.out.println(tAccount);
    }
}
