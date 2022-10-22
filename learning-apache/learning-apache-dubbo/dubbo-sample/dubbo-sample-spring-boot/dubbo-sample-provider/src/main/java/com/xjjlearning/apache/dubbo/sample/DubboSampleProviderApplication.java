package com.xjjlearning.apache.dubbo.sample;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.xjjlearning.apache.dubbo.sample")
public class DubboSampleProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleProviderApplication.class, args);
    }

}
