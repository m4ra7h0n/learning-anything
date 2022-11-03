package com.xjjlearning.alibaba.cloud.seata.account.dubbo;

import com.xjjlearning.alibaba.cloud.seata.account.service.TAccountServiceImpl;
import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.dubbo.AccountDubboService;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class AccountDubboServiceImpl implements AccountDubboService {
    @Resource
    TAccountServiceImpl tAccountService;
    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        return tAccountService.decreaseAccount(accountDTO);
    }
}
