package com.xjjlearning.java.util.concurrent.syn;

import java.util.concurrent.TimeUnit;

public class Volatile {

    public static class Runner implements Runnable {
        private volatile boolean shutdownRequested = false;
        public void shutdown() {
            shutdownRequested = true;
        }
        @Override
        public void run() {
            while (!shutdownRequested && !Thread.currentThread().isInterrupted()) {
                // do stuff
                System.out.println('~');
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread = new Thread(runner);
        thread.start();
//        thread.setDaemon(true); // what ? shutdown method is useless
        TimeUnit.SECONDS.sleep(2);
        runner.shutdown();
        thread.interrupt();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("over");
    }
}
