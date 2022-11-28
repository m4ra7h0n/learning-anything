package com.xjjlearning.java.util.stream.chap11.v1;

import com.xjjlearning.java.util.stream.chap11.v1.service.AsyncShop;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@SpringBootTest
public class ShopTest {
    @Test
    public void asyncShopTest() {
        AsyncShop shop = new AsyncShop("XjjShop");
        long start = System.currentTimeMillis();
        Future<Double> phone = shop.getPrice("phone"); //An arbitrary virtual good
        long time1 = System.currentTimeMillis() - start;
        System.out.println("return after: " + time1);
        try {
            System.out.println("price is :" + phone.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println("get after: " + time2);
    }

    static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    @Test
    public void bestPriceFinderTest() {
        // execute("sequential find phone", () -> bestPriceFinder.findPrices("phone"));
        // execute("parallel find phone", () -> bestPriceFinder.findPricesParallel("phone"));
        // execute("CompletableFuture find phone", () -> bestPriceFinder.findPricessCompletableFuture("phone"));
        // execute("CompletableFutureExecutor find phone", () -> bestPriceFinder.findPricesCompletableFutureExecutor("phone"));
        execute("findPricesInRMB find phone", () -> bestPriceFinder.findPricesInRMB("phone"));
        execute("findPricesInRMBJava7 find phone", () -> bestPriceFinder.findPricesInUSDJava7("phone"));
    }
    public static void execute(String msg, Supplier<List<String>> s) {
        long start = System.currentTimeMillis();
        System.out.println(s.get());
        System.out.println(msg + " done: " + (System.currentTimeMillis() - start));
    }
}
