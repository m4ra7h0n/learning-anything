package com.xjjlearning.alibaba.cloud.seata.business;

import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.AccountDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
@EnableDubbo
class ExamplesBusinessApplicationTests {

    @DubboReference
    AccountDubboService accountDubboService;

    @Test
    void mybatisTest() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(String.valueOf(1));
        accountDTO.setAmount(BigDecimal.valueOf(1));
        ObjectResponse objectResponse = accountDubboService.decreaseAccount(accountDTO);
        System.out.println(objectResponse.getData());
    }
}
