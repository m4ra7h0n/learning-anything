package com.xjjlearning.hack.springboot.speldemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by xjj on 2023/4/20.
 */
@Component
public class SpelBeanDemo {
    @Value("你好, %{@name}, %{@msg}")
    private String desc;

    @Override
    public String toString() {
        return "SpelBeanDemo{" +
                "desc='" + desc + '\'' +
                '}';
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Configuration.class);
        context.refresh();
        SpelBeanDemo beanDemo = context.getBean(SpelBeanDemo.class);
        System.out.println(beanDemo);
    }
}
