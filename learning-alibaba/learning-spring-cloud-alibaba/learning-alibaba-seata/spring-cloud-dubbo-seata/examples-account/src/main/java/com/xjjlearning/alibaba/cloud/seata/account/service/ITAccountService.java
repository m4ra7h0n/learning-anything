package com.xjjlearning.alibaba.cloud.seata.account.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.AccountDTO;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;

public interface ITAccountService {
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
