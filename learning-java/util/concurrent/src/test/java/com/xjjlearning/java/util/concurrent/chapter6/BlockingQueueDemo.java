package com.xjjlearning.java.util.concurrent.chapter6;

import java.util.concurrent.*;

public class BlockingQueueDemo {
    private final long time;

    public BlockingQueueDemo(long time) {
        this.time = time;
    }

    public void allBlockingQueues() throws InterruptedException {
        // bounded
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>();

        // unbounded
        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>();
        DelayQueue<Delayed> delayQueue = new DelayQueue<>(); // useful. such as cache data
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>(); //
        LinkedTransferQueue<Object> linkedTransferQueue = new LinkedTransferQueue<>();
        LinkedBlockingDeque<Object> linkedBlockingDeque = new LinkedBlockingDeque<>();

        // delayQueue demo
        // Reference ScheduledThreadPoolExecutor
        delayQueue.offer(new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return unit.convert(time - now(), TimeUnit.NANOSECONDS);
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        });
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);

        // LinkedTransferQueue
        linkedTransferQueue.tryTransfer("abc");
        linkedTransferQueue.transfer("abc");
    }
    final long now() {
        return System.nanoTime();
    }
}
