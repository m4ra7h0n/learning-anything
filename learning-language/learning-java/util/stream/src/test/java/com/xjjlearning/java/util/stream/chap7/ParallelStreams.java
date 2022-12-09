package com.xjjlearning.java.util.stream.chap7;

import com.xjjlearning.java.util.stream.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class ParallelStreams {
    // At the bottom
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        //1.boxing and unboxing.
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        // 1.boxing and unboxing (long -> Long).
        // 2.Because of the iterator, the whole list is not ready yet, the parallel is failure.
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum(long n) {
        // 1.no boxing
        // 2.rangeClosed is not the same as iterate, it generates the list directly.
        return LongStream.rangeClosed(1, n).sum();
    }

    public static long parallelRangedSum(long n) {
        // 1.no boxing
        // 2.rangeClosed is not the same as iterate, it generates the list directly.
        // 3.parallel :)
        return LongStream.rangeClosed(1, n).parallel().sum();
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add); //competition
        return accumulator.total;
    }

    public static class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

}
