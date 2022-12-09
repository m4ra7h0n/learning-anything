package com.xjjlearning.alibaba.cloud.seata;

import com.xjjlearning.alibaba.cloud.seata.feign.StockFeign;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = StockFeign.class) //一定要加
public class OrderServiceApplication {

	public static void main(String[] args) {
//		GlobalTransactionScanner
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
