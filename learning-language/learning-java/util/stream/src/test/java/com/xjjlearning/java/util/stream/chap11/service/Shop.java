package com.xjjlearning.java.util.stream.chap11.service;

import java.util.Random;

import static com.xjjlearning.java.util.stream.chap11.Util.randomDelay;

public class Shop {
    public Shop(String name) {
        this.name = name;
        this.random = new Random((long) name.charAt(0) * name.charAt(1) + name.charAt(1));
    }

    private final String name;
    private final Random random;
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }
    public String getName() {
        return name;
    }
    public double calculatePrice(String product) {
        // delay(); // simulation delay
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(0);
    }
}
