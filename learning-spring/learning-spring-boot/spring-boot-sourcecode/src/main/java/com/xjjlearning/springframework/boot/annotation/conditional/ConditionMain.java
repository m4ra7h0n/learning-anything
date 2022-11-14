package com.xjjlearning.springframework.boot.annotation.conditional;

import com.xjjlearning.springframework.boot.annotation.conditional.classes.SpringService;
import com.xjjlearning.springframework.boot.annotation.conditional.classes.SpringServiceRequiredOnBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//condition的具体用途可以参考源码: RabbitAutoConfiguration
@SpringBootApplication
public class ConditionMain implements CommandLineRunner {
    @Autowired
    private SpringServiceRequiredOnBean springServiceRequiredOnBean;
    @Autowired
    private SpringService springService;

    public static void main(String[] args) {
        SpringApplication.run(ConditionMain.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ConditionOnBean: " + springServiceRequiredOnBean.getClass());
        System.out.println("ConditionNotOnWindows: " + springService.getClass());
    }
}
