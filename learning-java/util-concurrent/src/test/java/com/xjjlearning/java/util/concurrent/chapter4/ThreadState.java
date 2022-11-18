package com.xjjlearning.java.util.concurrent.chapter4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 6-3
 */
public class ThreadState {

    private static Lock lock = new ReentrantLock();

    // `jsp` -> find the PID of TreadState thread
    // `jstack` PID to get the thread info
    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start(); // time waiting (sleeping)
        new Thread(new Waiting(), "WaitingThread").start(); // wait() -> waiting
        new Thread(new Blocked(), "BlockedThread-1").start(); //get lock -> time waiting (sleeping)
        new Thread(new Blocked(), "BlockedThread-2").start(); //wait lock -> blocked
        new Thread(new Sync(), "SyncThread-1").start();
        new Thread(new Sync(), "SyncThread-2").start();
    }

    /**
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    /**
     */
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     */
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    static class Sync implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                SleepUtils.second(100);
            } finally {
                lock.unlock();
            }

        }

    }
}