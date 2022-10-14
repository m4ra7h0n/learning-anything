package com.xjjlearning.springbootdemo.listen.listener;


import com.xjjlearning.springbootdemo.listen.event.LoggingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingEventListener {
    @EventListener
    public void listener(LoggingEvent event){
        event.printMessage("with [@EventListener]");
    }
}
