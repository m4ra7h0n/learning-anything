package com.xjjlearning.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class SpringCloudGatewayNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayNacosConsumerApplication.class, args);
    }

}
