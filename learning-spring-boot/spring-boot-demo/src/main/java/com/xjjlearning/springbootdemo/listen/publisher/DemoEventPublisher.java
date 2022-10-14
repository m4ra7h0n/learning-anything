package com.xjjlearning.springbootdemo.listen.publisher;

import com.xjjlearning.springbootdemo.listen.event.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DemoEventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void pushEvent(String message){
        applicationEventPublisher.publishEvent(new DemoEvent(this));
    }
}
