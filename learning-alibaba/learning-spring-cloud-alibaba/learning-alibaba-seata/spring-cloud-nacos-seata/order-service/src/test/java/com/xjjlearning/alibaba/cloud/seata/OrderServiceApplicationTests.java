package com.xjjlearning.alibaba.cloud.seata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
		BigDecimal orderMoney = new BigDecimal(2).multiply(new BigDecimal(5));
		System.out.println(orderMoney);
	}

}
