package com.xjjlearning.apache.dubbbo.dubbosampleconsumer;

import com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Reference(url = "dubbo://127.0.0.1:20880/com.xjjlearning.apache.dubbo.dubbosampleapi.IHelloService")
    private IHelloService helloService;

    @GetMapping("/say/{string}")
    public String sayHello(@PathVariable String string){
        return helloService.sayHello(string);
    }
}
