package com.xjjlearning.springbootdemo.listen.event;

import com.xjjlearning.springbootdemo.listen.event.iface.Event;
import lombok.Data;
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
