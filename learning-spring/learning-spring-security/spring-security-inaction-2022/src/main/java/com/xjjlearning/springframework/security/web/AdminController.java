package com.xjjlearning.springframework.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by xjj on 2022/12/13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
    @GetMapping("/super")
    public String superSayHello() {
        return "super admin";
    }
}
