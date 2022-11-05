package com.xjjlearning.alibaba.seata;


import io.seata.spring.annotation.datasource.SeataDataSourceBeanPostProcessor;
import io.seata.spring.boot.autoconfigure.HttpAutoConfiguration;
import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import io.seata.spring.boot.autoconfigure.SeataDataSourceAutoConfiguration;
import io.seata.spring.boot.autoconfigure.SeataPropertiesAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeataSourcecodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataSourcecodeApplication.class, args);
    }

}
