package com.xjjlearning.nacosdiscoveryprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NacosDiscoveryProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryProviderApplication.class, args);
    }
    @RestController
    public class EchoController{
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string){
            return "Hello Nacos Discovery " + string;
        }

    }


}
