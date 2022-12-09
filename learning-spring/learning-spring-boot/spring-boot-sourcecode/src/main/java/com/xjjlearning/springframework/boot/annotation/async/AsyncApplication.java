package com.xjjlearning.springframework.boot.annotation.async;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

@EnableAsync
@SpringBootApplication
public class AsyncApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class);
    }
    @Resource
    AsyncService asyncService;
    private static final int TASK_NUM = 10;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < TASK_NUM; i++) {
            asyncService.executeAsyncTaskA(i);
            asyncService.executeAsyncTaskB(i);
        }
    }
}
