package com.xjjlearning.java.util.concurrent;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

@SuppressWarnings("all")
public class Source {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

        // https://blog.csdn.net/weixin_43591980/article/details/116146008
        LongAdder longAdder = new LongAdder();

        // https://csp1999.blog.csdn.net/article/details/116502499
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(1);

        concurrentHashMap.put("a", "1");
        concurrentHashMap.put("b", "2");


        /*
            https://tech.meituan.com/2016/11/18/disruptor.html
            singleThread > cas > lock
         */

        // False sharing
        // bounded, lock, arraylist
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        // bounded, lock, linkedlist
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>();
        LinkedBlockingDeque<Object> linkedBlockingDeque = new LinkedBlockingDeque<>();

        // unbounded, cas, linkedlist
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
        LinkedTransferQueue<Object> linkedTransferQueue = new LinkedTransferQueue<>();
        ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        // unbounded, lock, heap
        DelayQueue<Delayed> delayQueue = new DelayQueue<>(); // useful. such as cache data
        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>();
    }

    static class CacheLineEffect {
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

    // https://www.cnblogs.com/cyfonly/p/5800758.html
    static class FalseSharing implements Runnable {
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
            protected long p1, p2, p3, p4, p5, p6, p7;
            protected volatile long value = 0L; // notice that this is a volatile object
            protected long p9, p10, p11, p12, p13, p14;
            protected long p15;
        }

        public static final class ValueNoPadding {
            // protected long p1, p2, p3, p4, p5, p6, p7;
            protected volatile long value = 0L;
            // protected long p9, p10, p11, p12, p13, p14, p15;
        }
    }
}
