package com.xjjlearning.java.util.stream.chap7;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

@SpringBootTest
public class ParallelStreamsHarnessTest {
    @Test
    public void parallelStreamsHarnessTest() {
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Parallel Sum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs");
        System.out.println("Ranged Sum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        System.out.println("Parallel Ranged Sum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs");
        System.out.println("ForkJoin Sum done in: " + measurePerf(ParallelStreams::forkJoinSum, 10_000_000L) + " msecs");
    }
    @Test
    public void sideEffectSumTest() {
        System.out.println("SideEffect Sum done in: " + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs");
    }

    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            R result = f.apply(input);
            long duration = System.currentTimeMillis() - start;
            System.out.println("Result: " + result);
            fastest = Math.min(duration, fastest);
        }
        return fastest;
    }

    @Test
    public void coreTest() {
        System.out.println(Runtime.getRuntime().availableProcessors()); // 4cores, Hyperthreading technology, -> 8 availableProcessors
    }
}
