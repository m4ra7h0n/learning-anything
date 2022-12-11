package com.xjjlearning.springframework.boot.selector;

import com.xjjlearning.springframework.boot.selector.classes.MemberRegisterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
// @EnableAutoImport
public class ImportSelectorMain {
    @Resource
    MemberRegisterService registerService;
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ImportSelectorMain.class);

        /**
         *  if open the @EnableAutoImport, then can auto-inject the FirstClass
         */
        // FirstClass firstClass = run.getBean(FirstClass.class);

    }
}
