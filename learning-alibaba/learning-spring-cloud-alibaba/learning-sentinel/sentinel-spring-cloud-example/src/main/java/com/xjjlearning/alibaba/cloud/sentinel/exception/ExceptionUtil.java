package com.xjjlearning.alibaba.cloud.sentinel.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public final class ExceptionUtil {
    //必须为static函数
    public static String blockHandlerHello(BlockException e) {
        e.printStackTrace();
        return "Oops you are blocked !";
    }
}
