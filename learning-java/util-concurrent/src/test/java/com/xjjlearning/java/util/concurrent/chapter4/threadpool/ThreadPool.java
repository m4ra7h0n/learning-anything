package com.xjjlearning.java.util.concurrent.chapter4.threadpool;

/**
 * 6-19
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
