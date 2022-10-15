package com.xjjlearning.springcloud.nacossamplespringboot.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/get")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
    @PostMapping(value = "/create")
    public void create(String serviceName, String ip, Integer port) throws NacosException{
        namingService.registerInstance(serviceName, ip, port, "DEFAULT");
    }
    //监听服务端异常情况
    @GetMapping(value = "/subscribe")
    public void subscribe(String serviceName, EventListener listener) throws NacosException{
        namingService.subscribe(serviceName, listener);
    }
}