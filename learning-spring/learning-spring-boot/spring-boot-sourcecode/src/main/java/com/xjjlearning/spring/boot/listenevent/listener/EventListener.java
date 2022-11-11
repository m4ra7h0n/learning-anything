package com.xjjlearning.spring.boot.listenevent.listener;

import com.xjjlearning.spring.boot.listenevent.event.DemoEvent;
import com.xjjlearning.spring.boot.listenevent.event.LoggingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class EventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof DemoEvent){
            ((DemoEvent) event).printMessage("with [instanceofEventListener]");
        } else if (event instanceof LoggingEvent){
            ((LoggingEvent) event).printMessage("with [instanceofEventListener]");
        }
    }
}



/**
 * 方式3  使用@EventListener注解
 */
//@Component
//public class EventListenerDemo {
//    @EventListener
//    public void listener(EventDemo event) { //只需要添加EventListener注解以及传入相应的Event即可
//        System.out.println("事件触发: " + event.getClass().getName());
//    }
//}
