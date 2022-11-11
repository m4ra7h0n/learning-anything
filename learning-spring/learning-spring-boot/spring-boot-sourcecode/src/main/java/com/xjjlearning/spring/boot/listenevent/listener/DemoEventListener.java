package com.xjjlearning.spring.boot.listenevent.listener;

import com.xjjlearning.spring.boot.listenevent.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoEventListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        event.printMessage(String.format("with [ApplicationListener<%s>]", event.getClass().getName()));
    }
}
