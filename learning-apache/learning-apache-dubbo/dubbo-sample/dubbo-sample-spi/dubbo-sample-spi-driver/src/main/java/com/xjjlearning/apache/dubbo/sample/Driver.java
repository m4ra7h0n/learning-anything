package com.xjjlearning.apache.dubbo.sample;

import org.apache.dubbo.common.extension.SPI;

@SPI //dubbo扩展点需要添加此注解
public interface Driver {
    String connect();
}
