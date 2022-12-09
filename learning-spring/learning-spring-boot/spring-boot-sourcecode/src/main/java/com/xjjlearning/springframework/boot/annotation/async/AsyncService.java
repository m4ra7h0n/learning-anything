package com.xjjlearning.springframework.boot.annotation.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Async
    public void executeAsyncTaskA(Integer i) throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("异步执行任务A" + i);
    }

    @Async
    public void executeAsyncTaskB(Integer i) throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("异步执行任务B" + i);
    }

}
