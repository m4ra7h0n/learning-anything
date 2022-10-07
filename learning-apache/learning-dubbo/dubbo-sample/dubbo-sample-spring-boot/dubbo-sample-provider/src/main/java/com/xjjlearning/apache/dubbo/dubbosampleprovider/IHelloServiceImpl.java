package com.xjjlearning.apache.dubbo.dubbosampleprovider;

import com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class IHelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello";
    }
}
