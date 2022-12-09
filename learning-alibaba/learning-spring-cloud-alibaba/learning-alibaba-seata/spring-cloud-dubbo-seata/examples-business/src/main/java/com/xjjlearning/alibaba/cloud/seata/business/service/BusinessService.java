package com.xjjlearning.alibaba.cloud.seata.business.service;

import com.xjjlearning.alibaba.cloud.seata.common.dto.BusinessDTO;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;

public interface BusinessService {
    ObjectResponse handleBusiness(BusinessDTO businessDTO);
}
