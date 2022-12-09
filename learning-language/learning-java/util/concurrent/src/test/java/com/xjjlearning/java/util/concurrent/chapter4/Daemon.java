package com.xjjlearning.java.util.concurrent.chapter4;

/**
 * 6-5
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(100);
            } finally {
                // not execute
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
