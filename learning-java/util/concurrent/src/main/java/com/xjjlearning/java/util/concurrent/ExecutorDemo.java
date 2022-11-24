package com.xjjlearning.java.util.concurrent;

import java.util.concurrent.*;

public class ExecutorDemo {
    public static void f(String[] args) throws ExecutionException, InterruptedException {
        // create executors
        int nThreads = 3;
        Executor executor1 = command -> {};
        ExecutorService executor2 = Executors.newFixedThreadPool(nThreads);
        ExecutorService executor3 = Executors.newSingleThreadExecutor();
        ExecutorService executor4 = Executors.newCachedThreadPool();
        ThreadPoolExecutor executor5 = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        /*interface*/
        ScheduledExecutorService executor6 = Executors.newScheduledThreadPool(nThreads);
        ScheduledExecutorService executor7 = Executors.newSingleThreadScheduledExecutor();
        /*implements*/
        ScheduledThreadPoolExecutor executor8 = new ScheduledThreadPoolExecutor(nThreads);


        // submit task by executor
        Future<?> res1 = executor2.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 1;
        });// callable

        ScheduledFuture<?> res2 = executor7.scheduleAtFixedRate(() -> {
            System.out.println("run "+ System.currentTimeMillis());
        },0, 2, TimeUnit.SECONDS); // Minimum interval time
        // start at 1s and return result async
        ScheduledFuture<?> res3 = executor7.schedule(() -> "A", 1, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> res4 = executor7.scheduleWithFixedDelay(() -> {
            System.out.println("ok");
        }, 1, 2, TimeUnit.SECONDS); // start at 1s and execute every 1s fixedly
        DelayQueue<Delayed> delayQueue = new DelayQueue<>(); //scheduleWithFixedDelay -> DelayQueue

        System.out.println(res1.get());
        res2.cancel(true);

        FutureTask<String> task = new FutureTask<>(() -> "abc");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(task);
        String s = task.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> r = executor.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run "+ System.currentTimeMillis());
        },0, 2, TimeUnit.SECONDS); // print every 3s

//        ScheduledFuture<?> r = executor.scheduleWithFixedDelay(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("run "+ System.currentTimeMillis());
//        }, 0, 2, TimeUnit.SECONDS); // print every 5s

    }
}
