package com.xjjlearning.apache.dubbo.dubbosamplespringcloudconsumer;

import com.xjjlearning.apache.dubbo.dubbosamplespringcloudapi.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDubbo
public class DubboSampleSpringCloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleSpringCloudConsumerApplication.class, args);
    }

    @RestController
    public class HelloController {
        //mock doesn't support on provider side
        //mock 返回降级数据 并且只能在消费端设置
        @Reference(check = false, mock = "com.xjjlearning.apache.dubbo.dubbosamplespringcloudconsumer.MockHelloService")
        private IHelloService helloService;

        @GetMapping("/say/{string}")
        public String sayHello(@PathVariable String string){
            System.out.println("Consumer says hello");
            return helloService.sayHello(string);
        }
    }
}
