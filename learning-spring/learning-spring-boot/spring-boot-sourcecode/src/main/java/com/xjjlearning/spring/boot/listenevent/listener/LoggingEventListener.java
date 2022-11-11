package com.xjjlearning.spring.boot.listenevent.listener;


import com.xjjlearning.spring.boot.listenevent.event.LoggingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingEventListener {
    @EventListener
    public void listener(LoggingEvent event){
        event.printMessage("with [@EventListener]");
    }
}
