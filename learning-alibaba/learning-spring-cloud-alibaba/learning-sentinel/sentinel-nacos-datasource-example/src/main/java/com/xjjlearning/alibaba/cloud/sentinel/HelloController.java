package com.xjjlearning.alibaba.cloud.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("say")
    public String hello() {
        return "hello, xjj";
    }
}
