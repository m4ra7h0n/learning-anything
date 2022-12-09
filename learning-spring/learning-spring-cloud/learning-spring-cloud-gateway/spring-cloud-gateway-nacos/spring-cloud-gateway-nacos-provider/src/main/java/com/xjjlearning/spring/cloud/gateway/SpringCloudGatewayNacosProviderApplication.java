package com.xjjlearning.spring.cloud.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringCloudGatewayNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayNacosProviderApplication.class, args);
    }

    @Value("${spring.application.name}")
    String applicationName;

    @RestController
    class HelloController {
        @GetMapping("/say")
        public String sayHello() {
            return "[" + applicationName + "]: say, hello";
        }
    }

}
