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
        LongAdder longAdder = new LongAdder();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(1);

        concurrentHashMap.put("a", "1");
        concurrentHashMap.put("b", "2");

        System.out.println(1 / 2);


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
