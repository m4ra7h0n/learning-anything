package com.xjjlearning.apache.dubbbo.dubbosampleconsumer;

import com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableDubbo
@SpringBootApplication
public class DubboSampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleConsumerApplication.class, args);
    }

    @RestController
    public class HelloController {

//        @Reference(url = "dubbo://127.0.0.1:20880/com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService")
        @DubboReference
        private IHelloService helloService;

        @GetMapping("/say/{string}")
        public String sayHello(@PathVariable String string){
            return helloService.sayHello(string);
        }
    }
}
