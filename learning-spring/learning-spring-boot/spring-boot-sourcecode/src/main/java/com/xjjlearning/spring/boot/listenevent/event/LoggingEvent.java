package com.xjjlearning.spring.boot.listenevent.event;

import com.xjjlearning.spring.boot.listenevent.event.iface.Event;
import org.springframework.context.ApplicationEvent;

public class LoggingEvent extends ApplicationEvent implements Event {
    public LoggingEvent(Object source) {
        super(source);
    }

    @Override
    public void printMessage(String message) {
        System.out.println("[LoggingEvent]: " + message);
    }
}
