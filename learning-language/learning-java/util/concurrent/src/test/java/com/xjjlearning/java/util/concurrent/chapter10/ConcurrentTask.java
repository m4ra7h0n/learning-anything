package com.xjjlearning.java.util.concurrent.chapter10;

import java.util.concurrent.*;

public class ConcurrentTask {

    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName); //1.1,2.1
            if (future == null) {
                Callable<String> task = () -> {
                    //......
                    return taskName;
                };
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask); //1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }

            try {
                return future.get();
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }

}
