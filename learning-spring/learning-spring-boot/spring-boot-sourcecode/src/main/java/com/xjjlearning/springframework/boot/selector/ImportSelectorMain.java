package com.xjjlearning.springframework.boot.selector;

import com.xjjlearning.springframework.boot.selector.classes.FirstClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoImport
public class ImportSelectorMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ImportSelectorMain.class);
        FirstClass firstClass = run.getBean(FirstClass.class);
    }
}
