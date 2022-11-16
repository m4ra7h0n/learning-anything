package com.xjjlearning.java.util.concurrent.syn;

public class Volatile {
    public volatile static boolean shutdownRequested = false;
    public void shutdown() {
        shutdownRequested = true;
    }

    public void doWork() {
        while (!shutdownRequested) {
            // do stuff
            System.out.println('~');
        }
    }

    // why it doesn't work ?
    public static void main(String[] args) throws InterruptedException {
        Volatile aVolatile = new Volatile();
        aVolatile.doWork();
        Thread.sleep(1);
        aVolatile.shutdown();
//        new Thread(aVolatile::shutdown).start();
    }
}
