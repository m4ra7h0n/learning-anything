package com.xjjlearning.alibaba.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/config")
@RefreshScope  //配置自动更新
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    //bootstrap.properties中设置的spring.application.name会作为DataId查询nacos
    //查询后会变成application.properties中的值, 可进行Value
    @Value("${content:nothing}")
    private String content;
    /**
     * http://localhost:8080/config/get
     */
    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping("/getContent")
    public String getTest() {
        return content;
    }
}
