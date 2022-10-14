package com.xjjlearning.springbootdemo.listen;

import com.xjjlearning.springbootdemo.listen.event.LoggingEvent;
import com.xjjlearning.springbootdemo.listen.multicaster.EventMulticaster;
import com.xjjlearning.springbootdemo.listen.publisher.DemoEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApplicationListenerDemoApplication {
    @Autowired
    DemoEventPublisher eventPublisherDemo;

    @RestController
    class EventPushController {
        @GetMapping("/publish/demo/{message}")
        public String publish(@PathVariable String message){
            eventPublisherDemo.pushEvent(message);
            return "publish success";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationListenerDemoApplication.class);
    }
}
