/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.xjjlearning.java.util.concurrent.chapter2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@SpringBootTest
public class CounterTest {

    private AtomicInteger atomicI = new AtomicInteger(0);
//    private AtomicStampedReference reference = new AtomicStampedReference(0);
    private AtomicReference reference = new AtomicReference();
    private int i = 0;

    @Test
    public void counterTest() {
        final CounterTest cas = new CounterTest();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }


    private void safeCount() {
//        atomicI.getAndIncrement();
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i); //先看下i是否还是i 然后通过锁缓存/总线 进行更新
            if (suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }

}
