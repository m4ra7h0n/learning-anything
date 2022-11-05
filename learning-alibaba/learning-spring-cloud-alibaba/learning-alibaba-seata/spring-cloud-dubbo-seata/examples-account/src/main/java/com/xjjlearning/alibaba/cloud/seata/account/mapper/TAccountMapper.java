package com.xjjlearning.alibaba.cloud.seata.account.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.account.model.TAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TAccountMapper extends BaseMapper<TAccount> {

    @Update("update t_account set amount = amount - #{amount} where user_id = #{userId}")
    int decreaseAccount(String userId, Double amount);

    int testGlobalLock(String userId);
}