package com.xjjlearning.alibaba.cloud.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    //该接口用于dashboard上配置
    @GetMapping("/dash")
    public String dash(){
        return "Oops Dashboard !!?";
    }
}
