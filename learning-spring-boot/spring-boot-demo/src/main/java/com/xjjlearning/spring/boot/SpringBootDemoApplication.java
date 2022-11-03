package com.xjjlearning.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
//        String
        HashMap<String, Integer> map = new HashMap<>();
        String s1 = "hello";
        String s2 = "hello";
        map.put(s1, 1);
        map.hashCode();

        SpringApplication.run(SpringBootDemoApplication.class, args);
//        ApplicationEnvironmentPreparedEvent
//        AnnotatedBeanDefinitionReader
    }

}
