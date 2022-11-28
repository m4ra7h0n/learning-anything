package com.xjjlearning.java.util.stream.chap11.v1.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.xjjlearning.java.util.stream.chap11.Util.delay;

public class AsyncShop {
    public AsyncShop(String name) {
        this.name = name;
        this.random = new Random((long) name.charAt(0) * name.charAt(1) + name.charAt(1));
    }

    private final String name;
    private final Random random;

    public Future<Double> getPrice(String product) {
        // return calculatePrice(product);

        // CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // new Thread( () -> {
        //     try {
        //         double price = calculatePrice(product);
        //         futurePrice.complete(price);
        //     } catch (Exception ex) {
        //         futurePrice.completeExceptionally(ex);
        //     }
        // }).start();
        // return futurePrice;

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    public double calculatePrice(String product) {
        delay(); // simulation delay
        // throw new RuntimeException("");
        return random.nextDouble() * product.charAt(0) + product.charAt(0);
    }
}
