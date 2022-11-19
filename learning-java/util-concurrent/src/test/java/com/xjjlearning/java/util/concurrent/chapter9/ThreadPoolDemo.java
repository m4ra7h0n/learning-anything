package com.xjjlearning.java.util.concurrent.chapter9;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                r -> new Thread("hello"),
                new ThreadPoolExecutor.AbortPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before Execute");
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after Execute");
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                System.out.println("terminated !");
                super.terminated();
            }
        };

        executor.execute(()->{}); // one single task

        Future<?> future = executor.submit(() -> {}); // need return value
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            executor.shutdown();
//            executor.shutdownNow();
        }

        System.out.println(executor.getActiveCount());
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getLargestPoolSize());
        System.out.println(executor.getTaskCount());
        System.out.println(executor.getCompletedTaskCount());
    }
}
