package com.xjjlearning.alibaba.cloud.seata.common.exception;

import com.xjjlearning.alibaba.cloud.seata.common.enums.RspStatusEnum;

public class DefaultException extends RuntimeException{
    private RspStatusEnum rspStatusEnum;

    public DefaultException(RspStatusEnum rspStatusEnum) {
        super(rspStatusEnum.getMessage());
        this.rspStatusEnum = rspStatusEnum;
    }
}
