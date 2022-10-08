package com.xjjlearning.apache.dubbo.dubbosampleprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.xjjlearning.apache.dubbo.dubbosampleprovider")
public class DubboSampleProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleProviderApplication.class, args);
    }

}
