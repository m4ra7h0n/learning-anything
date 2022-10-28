package com.xjjlearning.alibaba.cloud.seata.account.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TAccountMapper extends BaseMapper<TAccount> {

    int decreaseAccount(String userId, Double amount);

    int testGlobalLock(String userId);
}