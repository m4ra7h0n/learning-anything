package com.xjjlearning.spring.boot.listenevent.event;

import com.xjjlearning.spring.boot.listenevent.event.iface.Event;
import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent implements Event {
    public DemoEvent(Object source) {
        super(source);
    }

    @Override
    public void printMessage(String message) {
        System.out.println("[DemoEvent]: " + message);
    }
}
