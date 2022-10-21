package com.xjjlearning.alibaba.cloud;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xjjlearning.alibaba.cloud.exception.ExceptionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SentinelSpringCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelSpringCloudApplication.class, args);
	}
}
