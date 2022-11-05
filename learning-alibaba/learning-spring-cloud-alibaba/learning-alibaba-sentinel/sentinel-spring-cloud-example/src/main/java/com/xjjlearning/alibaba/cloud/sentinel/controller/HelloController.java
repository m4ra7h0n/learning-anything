package com.xjjlearning.alibaba.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xjjlearning.alibaba.cloud.sentinel.exception.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    @SentinelResource(value = "sayHello", blockHandler = "blockHandlerHello", blockHandlerClass = ExceptionUtil.class)
    public String hello() {
        System.out.println("hello, xjj");
        return "hello, xjj";
    }

}
