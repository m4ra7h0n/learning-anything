package com.xjjlearning.alibaba.cloud.seata.account.service;

import com.xjjlearning.alibaba.cloud.seata.account.mapper.TAccountMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class TAccountServiceImpl implements ITAccountService{
    @Resource
    TAccountMapper tAccountMapper;

    @Override
    @Transactional
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        ObjectResponse objectResponse = new ObjectResponse();

        int amount = tAccountMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());

        if (amount > 0) {
            objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
            objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return objectResponse;
        }

        objectResponse.setStatus(RspStatusEnum.FAIL.getCode());
        objectResponse.setMessage(RspStatusEnum.FAIL.getMessage());
        return objectResponse;
    }
}
