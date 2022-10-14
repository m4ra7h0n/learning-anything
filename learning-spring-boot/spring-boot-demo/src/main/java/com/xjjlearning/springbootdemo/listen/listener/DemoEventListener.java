package com.xjjlearning.springbootdemo.listen.listener;

import com.xjjlearning.springbootdemo.listen.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoEventListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        event.printMessage(String.format("with [ApplicationListener<%s>]", event.getClass().getName()));
    }
}
