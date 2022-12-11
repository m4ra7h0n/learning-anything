package com.xjjlearning.springframework.boot.conditional;

import com.xjjlearning.springframework.boot.conditional.classes.SpringService;
import com.xjjlearning.springframework.boot.conditional.classes.SpringServiceRequiredOnBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

//condition的具体用途可以参考源码: RabbitAutoConfiguration
@SpringBootApplication
public class ConditionMain implements CommandLineRunner {
    @Resource
    private SpringServiceRequiredOnBean springServiceRequiredOnBean;
    @Resource
    private SpringService springService;
    @Resource
    private String defaultBean;

    public static void main(String[] args) {
        SpringApplication.run(ConditionMain.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ConditionOnBean: " + springServiceRequiredOnBean.getClass());
        System.out.println("ConditionNotOnWindows: " + springService.getClass());
        System.out.println("onMissingClass \"MissingBeanClass\" load defaultBean: " + defaultBean.getClass());
    }
}
