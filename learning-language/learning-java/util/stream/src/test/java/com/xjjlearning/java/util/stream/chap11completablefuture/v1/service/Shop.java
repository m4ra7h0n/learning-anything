package com.xjjlearning.java.util.stream.chap11completablefuture.v1.service;

import java.util.Random;

import static com.xjjlearning.java.util.stream.chap11completablefuture.Util.delay;

public class Shop {
    public Shop(String name) {
        this.name = name;
        this.random = new Random((long) name.charAt(0) * name.charAt(1) + name.charAt(1));
    }

    private final String name;
    private final Random random;
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    public String getName() {
        return name;
    }
    public double calculatePrice(String product) {
        delay(); // simulation delay
        return random.nextDouble() * product.charAt(0) + product.charAt(0);
    }
}
