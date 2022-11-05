package com.xjjlearning.alibaba.cloud.seata.common.enums;

import lombok.Getter;

@Getter
public enum RspStatusEnum {
    SUCCESS(200, "成功"),
    FAIL(999, "失败"),
    EXCEPTION(500, "系统异常");

    private final int code;
    private final String message;

    RspStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
