package com.xjjlearning.java.util.stream.chap11;

import com.xjjlearning.java.util.stream.chap11.service.Discount;
import com.xjjlearning.java.util.stream.chap11.service.Quote;
import com.xjjlearning.java.util.stream.chap11.service.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(
            // each task -> 1s + 1s
            // 8 tasks -> 8 cores -> sequentialStream -> 16s
            // 8 tasks -> 8 cores -> parallelStream -> 2s
            // 9 tasks -> 8 cores -> parallelStream -> 2s + 2s
            // n(n > 8) tasks -> 8 cores -> thread pool(n) + completableFuture -> 2s
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    // try to deal with the situation if the provided method is sync
    // use stream sequential
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(s -> s.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    // use stream parallel
    public List<String> findPricesParallel(String product) {
        return shops.stream().parallel()
                .map(s -> s.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    // IO intensive -> set executor core size bigger
    // use CompletableFuture
    // nice
    public List<String> findPricesCompletableFutureExecutor(String product) {
        Stream<CompletableFuture<String>> completableFutureStream = findPricesStream(product);
        List<CompletableFuture<String>> tasks = completableFutureStream.collect(Collectors.toList());
        return tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        Executor executor = Executors.newCachedThreadPool();

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future ->
                        future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                        ));
    }

    // try thenAccept firstly :)
    public void printPricesStream(String product) {
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = findPricesStream(product)
                .map(future -> future.thenAccept(s ->
                        System.out.println(s + "(done in " + (System.currentTimeMillis() - start)+ " time)")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + (System.currentTimeMillis() - start) + " msecs");
    }
}
