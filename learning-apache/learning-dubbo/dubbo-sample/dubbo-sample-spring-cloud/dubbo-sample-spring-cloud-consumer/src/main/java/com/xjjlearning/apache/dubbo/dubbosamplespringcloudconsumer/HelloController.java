package com.xjjlearning.apache.dubbo.dubbosamplespringcloudconsumer;


import com.xjjlearning.apache.dubbo.dubbosamplespringcloudapi.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Reference(check = false)
    private IHelloService helloService;

    @GetMapping("/say/{string}")
    public String sayHello(@PathVariable String string){
        System.out.println("Consumer says hello");
        return helloService.sayHello(string);
    }
}
