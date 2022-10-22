package com.xjjlearning.alibaba.cloud.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@NacosPropertySource(dataId = "config", autoRefreshed = true)
public class ConfigController {
    @NacosValue(value = "${content:nothing}", autoRefreshed = true)
    private String content;

    @RequestMapping(value = "/get")
    public String get() {
        return content;
    }
}