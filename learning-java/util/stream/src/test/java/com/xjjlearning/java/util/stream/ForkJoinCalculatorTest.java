package com.xjjlearning.java.util.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

@SpringBootTest
public class ForkJoinCalculatorTest {
    @Test
    public void forkJoinSumCalculator() {
        long[] longs = LongStream.rangeClosed(1, 100000).toArray();
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(longs);
        Long invoke = new ForkJoinPool().invoke(forkJoinSumCalculator);
        System.out.println(invoke);
    }
}
