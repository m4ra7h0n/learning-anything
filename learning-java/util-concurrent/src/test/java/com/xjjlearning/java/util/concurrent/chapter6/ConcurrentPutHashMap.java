/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.xjjlearning.java.util.concurrent.chapter6;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentPutHashMap {

    public static void main(String[] args) throws InterruptedException {
//        final HashMap<String, String> map = new HashMap<>(2);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
