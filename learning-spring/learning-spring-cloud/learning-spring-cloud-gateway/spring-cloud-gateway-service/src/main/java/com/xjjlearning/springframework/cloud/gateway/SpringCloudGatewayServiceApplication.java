package com.xjjlearning.springframework.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringCloudGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayServiceApplication.class, args);
    }
    @RestController
    public static class HelloController {
        @GetMapping("/say")
        public String sayHello() {
            return "[spring-cloud-gateway-service]: say Hello";
        }
    }

}
