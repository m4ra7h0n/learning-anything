package com.xjjlearning.apache.dubbo.dubbosamplespringcloudprovider;

import com.xjjlearning.apache.dubbo.dubbosamplespringcloudapi.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

//集群容错中 failfast用于快速失败, 防止密等写操作重复
//负载均衡中 使用roundrobin, 在服务端集群中按照公约后的权重设置轮训比例
@Service(cluster = "failfast", loadbalance = "roundrobin")
public class HelloServiceImpl implements IHelloService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s]：Hello,%s",serviceName, name);
    }
}

