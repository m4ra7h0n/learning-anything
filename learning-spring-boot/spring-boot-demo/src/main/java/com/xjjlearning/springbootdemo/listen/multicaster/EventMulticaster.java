package com.xjjlearning.springbootdemo.listen.multicaster;

import com.xjjlearning.springbootdemo.listen.event.DemoEvent;
import com.xjjlearning.springbootdemo.listen.event.iface.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;


/**
 * 忽略这个
 */
public class EventMulticaster{}

//@Component
//public class EventMulticaster implements ApplicationEventMulticaster {
//
//    private final ConfigurableBeanFactory configurableBeanFactory;
//
//    private List<ApplicationListener<?>> listenerList;
//
//    @Autowired
//    public EventMulticaster(ConfigurableBeanFactory configurableBeanFactory) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        this.configurableBeanFactory = configurableBeanFactory;
//
//        String packageName = "com.xjjlearning.springbootdemo.listen.listener";
//        String packagesUrl=this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/")).getFile();
//        File scanFile = new File(packagesUrl);
//        String[] fileList = scanFile.list();
//        for (String fileName : fileList) {
//            listenerList.add((ApplicationListener<?>) Class.forName(packagesUrl + "." + fileName).newInstance());
//        }
//    }
//
//    @Override
//    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
//        //判空
//        ResolvableType type = (eventType != null ? eventType : ResolvableType.forInstance(event));
//
//        //如果来自自定义事件,才进行事件分发（不建议这样，这里只是演示示例）
//        if (type.isAssignableFrom(Event.class)){
//            //对监听器遍历处理事件
//            for (final ApplicationListener listener : listenerList){
//                listener.onApplicationEvent(event);
//            }
//        }
//    }
//    @Override
//    public void addApplicationListener(ApplicationListener<?> listener) {
//        listenerList.add(listener);
//    }
//
//    @Override
//    public void addApplicationListenerBean(String listenerBeanName) {
//        listenerList.add((ApplicationListener<?>) configurableBeanFactory.getBean(listenerBeanName));
//    }
//
//    @Override
//    public void removeApplicationListener(ApplicationListener<?> listener) {
//        listenerList.remove(listener);
//    }
//
//    @Override
//    public void removeApplicationListenerBean(String listenerBeanName) {
//        listenerList.remove((ApplicationListener<?>)configurableBeanFactory.getBean(listenerBeanName));
//    }
//
//    @Override
//    public void removeApplicationListeners(Predicate<ApplicationListener<?>> predicate) {
//    }
//
//    @Override
//    public void removeApplicationListenerBeans(Predicate<String> predicate) {
//    }
//
//    @Override
//    public void removeAllListeners() {
//        listenerList.clear();
//    }
//
//    @Override
//    public void multicastEvent(ApplicationEvent event) {
//        multicastEvent(event, ResolvableType.forInstance(event));
//    }
//
//
//}
