package com.xjjlearning.alibaba.cloud.seata.business;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
public class ExamplesBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamplesBusinessApplication.class, args);
    }

}
