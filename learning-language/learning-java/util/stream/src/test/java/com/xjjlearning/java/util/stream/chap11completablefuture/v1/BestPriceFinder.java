package com.xjjlearning.java.util.stream.chap11completablefuture.v1;


import com.xjjlearning.java.util.stream.chap11completablefuture.v1.service.ExchangeService;
import com.xjjlearning.java.util.stream.chap11completablefuture.v1.service.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.xjjlearning.java.util.stream.chap11completablefuture.v1.service.ExchangeService.getRate;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(
            // 8 tasks -> 8 cores -> sequentialStream -> 8s
            // 8 tasks -> 8 cores -> parallelStream -> 1s
            // 9 tasks -> 8 cores -> parallelStream -> 1s + 1s
            // n(n > 8) tasks -> 8 cores -> thread pool(n) + completableFuture -> 1s
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),

            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),

            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),

            new Shop("BestPrice"),
            new Shop("LetsSaveBig")
            // new Shop("MyFavoriteShop"),
            // new Shop("BuyItAll"),
    );

    // try to deal with the situation if the provided method is sync
    // use stream sequential
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(s -> String.format("%s price is %.2f", s.getName(), s.getPrice(product)))
                .collect(Collectors.toList());
    }

    // use stream parallel
    public List<String> findPricesParallel(String product) {
        return shops.stream().parallel()
                .map(s -> String.format("%s price is %.2f", s.getName(), s.getPrice(product)))
                .collect(Collectors.toList());
    }

    // use completableFuture
    public List<String> findPricessCompletableFuture(String product) {
        List<CompletableFuture<String>> tasks = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", s.getName(), s.getPrice(product))))
                .collect(Collectors.toList());
        return tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // IO intensive -> set executor core size bigger
    // use CompletableFuture
    public List<String> findPricesCompletableFutureExecutor(String product) {
        Executor executor = Executors.newFixedThreadPool(
                Math.min(shops.size(), 100), // IO intensive
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                });

        List<CompletableFuture<String>> tasks = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", s.getName(), s.getPrice(product)),
                        executor))
                .collect(Collectors.toList());
        return tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }




    // combine two tasks which can execute concurrently
    // why slower ???????
    public List<String> findPricesInRMB(String product) {
        Executor executor = Executors.newCachedThreadPool();

        List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)
                .thenCombine(
                    CompletableFuture.supplyAsync(() -> getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                    (price, rate) -> price * rate
                ).thenApply(price -> shop.getName() + "price is: " + price))
            .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .map(price -> /*shop.getName() +*/ " price is " + price)
                .collect(Collectors.toList());
    }




    // faster than CompletableFuture ?? :) why ?
    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD));
            Future<Double> futurePriceInUSD = executor.submit(() -> {
                try {
                    double priceInEUR = shop.getPrice(product);
                    return priceInEUR * futureRate.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

}
