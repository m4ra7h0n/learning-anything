package com.xjjlearning.java.util.concurrent;


import java.util.HashMap;
import java.util.PriorityQueue;
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

        // https://tech.meituan.com/2016/11/18/disruptor.html
        // singleThread > cas > lock

        // False sharing
        // bounded, lock, arraylist
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        // bounded, lock, linkedlist
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>();
        LinkedBlockingDeque<Object> linkedBlockingDeque = new LinkedBlockingDeque<>();

        // unbounded, lock, heap
        DelayQueue<Delayed> delayQueue = new DelayQueue<>(); // useful. such as cache data
        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>();

        // unbounded, volatile + cas, linkedlist   ->   hard to read
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
        LinkedTransferQueue<Object> linkedTransferQueue = new LinkedTransferQueue<>();
        // https://blog.csdn.net/qq_38293564/article/details/80798310
        ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new ConcurrentLinkedQueue<>(); /*! important */
        concurrentLinkedQueue.offer("abc");
        concurrentLinkedQueue.offer("def");
        concurrentLinkedQueue.poll();

        // https://developer.aliyun.com/article/574408
        FutureTask futureTask = new FutureTask<Integer>(() -> 1);

        // https://www.jianshu.com/p/925dba9f5969
        // http://www.ideabuffer.cn/2017/04/04/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3Java%E7%BA%BF%E7%A8%8B%E6%B1%A0%EF%BC%9AThreadPoolExecutor/
        // DelayedWorkQueue
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);
        scheduledThreadPoolExecutor.schedule(()->{}, 0, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();

        // when the blckingqueue is full, then ThreadPoolExecutor.Worker.run() will be executed.
        // https://www.cnblogs.com/yougewe/p/12267274.html
        // ThreadPoolExecutor

        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
    }
}
