package com.xjjlearning.java.util.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xjjlearning.java.util.stream.IsPrime.*;
import static java.lang.System.currentTimeMillis;

@SpringBootTest
public class IsPrimeTest {
    @Test
    public void isPrimeTest() {
        final int PRIMECOUNT = 1000000;
        long fastest = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = currentTimeMillis();
            partitionPrimesWithInlineCollector(PRIMECOUNT);
            long duration = (currentTimeMillis() - start); //ms
            fastest = Math.min(fastest, duration);
        }
        System.out.println("Fastest execution done: " + fastest + "ms");

        fastest = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = currentTimeMillis();
            partitionPrime(PRIMECOUNT);
            long duration = (currentTimeMillis() - start); //ms
            fastest = Math.min(fastest, duration);
        }
        System.out.println("Fastest execution done: " + fastest + "ms");

        // Very poor performance
        fastest = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = currentTimeMillis();
            getPrimeList(PRIMECOUNT);
            long duration = (currentTimeMillis() - start); //ms
            fastest = Math.min(fastest, duration);
        }
        System.out.println("Fastest execution done: " + fastest + "ms");
    }
}
