package com.xjjlearning.alibaba.cloud.seata.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjectResponse<T> extends BaseResponse implements Serializable {
    private T data;
}
