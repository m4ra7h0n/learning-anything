package com.xjjlearning.java.util.concurrent.chapter5;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10-20
 */
public class ConditionUseCaseTest {
    //condition -> lock
    //wait -> synchronized
    Lock      lock      = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Test
    public void conditionTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                conditionWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                conditionSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("thread-1: wait here");
            condition.await(); //release lock and wait here
            System.out.println("thread-1: continue");
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("thread-2: signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
