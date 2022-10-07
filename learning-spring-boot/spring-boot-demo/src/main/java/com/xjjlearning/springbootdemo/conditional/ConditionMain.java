package com.xjjlearning.springbootdemo.conditional;

import com.xjjlearning.springbootdemo.conditional.classes.SpringServiceRequiredOnBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//condition的具体用途可以参考源码: RabbitAutoConfiguration
@SpringBootApplication
public class ConditionMain implements CommandLineRunner {
    @Autowired
    private SpringServiceRequiredOnBean springServiceRequiredOnBean;

    public static void main(String[] args) {
        SpringApplication.run(ConditionMain.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ConditionOnBean: " + springServiceRequiredOnBean.getClass());
    }
}
