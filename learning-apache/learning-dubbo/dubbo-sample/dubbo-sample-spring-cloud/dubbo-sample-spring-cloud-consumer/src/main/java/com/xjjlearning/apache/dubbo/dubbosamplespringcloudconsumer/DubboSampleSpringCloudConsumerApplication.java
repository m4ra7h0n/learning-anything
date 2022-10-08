package com.xjjlearning.apache.dubbo.dubbosamplespringcloudconsumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDubbo
public class DubboSampleSpringCloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleSpringCloudConsumerApplication.class, args);
    }

}
