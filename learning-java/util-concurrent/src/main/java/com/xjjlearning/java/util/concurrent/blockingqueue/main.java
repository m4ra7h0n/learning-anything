package com.xjjlearning.java.util.concurrent.blockingqueue;

import java.util.concurrent.*;

public class main {
    public static void main(String[] args) {
//        LinkedTransferQueue
//        BlockingQueue
//        SynchronousQueue
//        LinkedBlockingQueue

        int i = 10;
        long start = System.nanoTime();
        while (i-- > 0) {
            System.out.println((System.nanoTime() - start) / 1000000000.0);
        }
    }
}
