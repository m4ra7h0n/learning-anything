package com.xjjlearning.alibaba.cloud.sentinel;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 暂时没搞
@SpringBootApplication
@EnableDubbo
public class SentinelApacheDubboAdapterExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApacheDubboAdapterExampleApplication.class, args);
	}

}
