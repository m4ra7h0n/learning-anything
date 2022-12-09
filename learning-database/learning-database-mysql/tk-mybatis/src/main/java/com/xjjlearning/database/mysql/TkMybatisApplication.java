package com.xjjlearning.database.mysql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class TkMybatisApplication implements CommandLineRunner {
    @Resource
    ServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.test();
    }
}
