package com.xjjlearning.java.util.concurrent;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class Source {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

//        https://blog.csdn.net/weixin_43591980/article/details/116146008
        LongAdder longAdder = new LongAdder();

//        https://csp1999.blog.csdn.net/article/details/116502499
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(1);

        concurrentHashMap.put("a", "1");
        concurrentHashMap.put("b", "2");


//        https://tech.meituan.com/2016/11/18/disruptor.html
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
}
