package com.xjjlearning.springframework.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by xjj on 2022/12/5
 */
@RestController
public class HelloController {
    @GetMapping("/")
    public String sayHello() {
        return "hello !";
    }
}
