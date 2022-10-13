package com.xjjlearning.apache.dubbo.dubbosampleprovider;

import com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
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
