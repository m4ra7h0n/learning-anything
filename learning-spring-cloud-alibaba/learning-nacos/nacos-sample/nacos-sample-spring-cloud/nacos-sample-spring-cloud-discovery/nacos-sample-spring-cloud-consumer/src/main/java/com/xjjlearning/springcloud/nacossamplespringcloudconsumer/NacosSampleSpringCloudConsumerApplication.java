package com.xjjlearning.springcloud.nacossamplespringcloudconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosSampleSpringCloudConsumerApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosSampleSpringCloudConsumerApplication.class, args);
    }

    @RestController
    class TestController{

        @Autowired
        RestTemplate restTemplate;

        @GetMapping(value = "/echo/{str}")
        public String echo(@PathVariable String str){
            return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
        }
    }

}
