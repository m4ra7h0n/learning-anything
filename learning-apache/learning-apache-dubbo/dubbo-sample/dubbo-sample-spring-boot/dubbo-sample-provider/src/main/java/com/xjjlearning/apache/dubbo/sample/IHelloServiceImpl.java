package com.xjjlearning.apache.dubbo.sample;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

//@Service
@DubboService
public class IHelloServiceImpl implements IHelloService {
    @Value("${dubbo.application.name}")
    private String serverName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s]: Hello, %s", serverName, name);
    }
}
