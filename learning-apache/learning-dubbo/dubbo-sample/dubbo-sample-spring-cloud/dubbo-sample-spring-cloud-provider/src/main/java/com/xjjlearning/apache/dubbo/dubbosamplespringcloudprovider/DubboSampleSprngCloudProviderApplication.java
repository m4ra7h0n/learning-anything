package com.xjjlearning.apache.dubbo.dubbosamplespringcloudprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@EnableDubbo(scanBasePackages = "com.xjjlearning.apache.dubbo.dubbosamplespringcloudprovider")
public class DubboSampleSprngCloudProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboSampleSprngCloudProviderApplication.class, args);
	}
}
