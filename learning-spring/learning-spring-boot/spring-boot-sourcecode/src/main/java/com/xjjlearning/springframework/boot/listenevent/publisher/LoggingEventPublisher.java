package com.xjjlearning.springframework.boot.listenevent.publisher;

import com.xjjlearning.springframework.boot.listenevent.event.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class LoggingEventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void pushEvent(String message){
        applicationEventPublisher.publishEvent(new LoggingEvent(this));
    }
}
