package com.xjjlearning.java.util.concurrent;


// https://www.cnblogs.com/cyfonly/p/5800758.html
public class FalseSharing implements Runnable {
    public final static long ITERATIONS = 500L * 1000L * 100L;
    static int NUM_THREADS = 4;
    private int arrayIndex = 0;

    private static ValuePadding[] longs;
//        private static ValueNoPadding[] longs;

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        long avgTime = 0, time;
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest();
            avgTime += (time = (System.currentTimeMillis() - start));
            System.out.println("Thread num " + i + " duration = " + time);  //amazing result !
        }
        System.out.println("average time: " + avgTime / 10);
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
//            longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValuePadding();
//                longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    // Increasing the spacing of array elements
    // so that elements accessed by different threads are located on different cache rows,
    // swapping space for time.
    public static final class ValuePadding {
        private long p1, p2, p3, p4, p5, p6, p7;
        private volatile long value = 0L; // notice that this is a volatile object
        private long p9, p10, p11, p12, p13, p14;
        private long p15;
    }

    public static final class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        private volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}

class CacheLineEffect {
    // Consider that the average cache line size is 64 bytes, and a long type takes up 8 bytes
    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8]; //long -> 8 Bytes
            // 8 * long -> 64.
            // one cache line
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) { // read every cache line
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms"); //26ms

        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) { //
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms"); //68ms
    }
}
