package com.xjjlearning.alibaba.cloud.seata.account.service;

import com.xjjlearning.alibaba.cloud.seata.account.mapper.TAccountMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class TAccountServiceImpl implements ITAccountService{
    @Resource
    TAccountMapper tAccountMapper;

    @Override
    @Transactional
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        System.out.println(RootContext.getXID());
        ObjectResponse objectResponse = new ObjectResponse();
        double amount = accountDTO.getAmount().doubleValue();
        // amount < 0 ??
        if (amount < 0) {
            throw new RuntimeException("[AccountError]: Do you want to increase money?");
        }

        TAccount tAccount = tAccountMapper.selectById(accountDTO.getUserId());
        if (tAccount == null) {
            throw new RuntimeException("[AccountError]: No Such Account");
        }
        if (tAccount.getAmount() < accountDTO.getAmount().doubleValue()) {
            throw new RuntimeException("[AccountError]: Account needs money");
        }

        // 更新
        tAccount.setAmount(tAccount.getAmount() - amount);
        tAccountMapper.updateById(tAccount);

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return objectResponse;
    }
}
