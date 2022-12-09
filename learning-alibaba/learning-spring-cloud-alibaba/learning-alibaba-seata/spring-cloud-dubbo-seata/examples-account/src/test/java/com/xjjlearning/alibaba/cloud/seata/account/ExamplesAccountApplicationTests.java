package com.xjjlearning.alibaba.cloud.seata.account;

import com.xjjlearning.alibaba.cloud.seata.account.mapper.TAccountMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import com.xjjlearning.alibaba.cloud.seata.account.service.TAccountServiceImpl;
import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class ExamplesAccountApplicationTests {
    @Resource
    TAccountMapper tAccountMapper;
    @DubboReference
    TAccountServiceImpl tAccountService;

    @Test
    void mybatisTest() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(String.valueOf(1));
        accountDTO.setAmount(BigDecimal.valueOf(-100));
        tAccountService.decreaseAccount(accountDTO);
    }
}
