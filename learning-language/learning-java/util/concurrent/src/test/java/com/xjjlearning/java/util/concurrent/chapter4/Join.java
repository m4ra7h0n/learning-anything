package com.xjjlearning.java.util.concurrent.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * 6-13
 */
public class Join {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private final Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join(); // 666
            } catch (InterruptedException ignored) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}