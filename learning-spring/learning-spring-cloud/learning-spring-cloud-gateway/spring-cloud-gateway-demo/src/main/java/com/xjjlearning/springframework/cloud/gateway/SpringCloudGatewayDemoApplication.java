package com.xjjlearning.springframework.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;

@SpringBootApplication
public class SpringCloudGatewayDemoApplication {

    public static void main(String[] args) {
//        RetryGatewayFilterFactory
//        RequestRateLimiterGatewayFilterFactory
        SpringApplication.run(SpringCloudGatewayDemoApplication.class, args);
    }

}
