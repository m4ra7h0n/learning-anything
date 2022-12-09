package com.xjjlearning.java.util.stream.chap13;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class FactorialTest {
    @Test
    public void factorialTest() {
        // reduce->identity: It stores the initial value of the reduction operation and also the default result when the stream of Integer values is empty.
        int recursion = IntStream.rangeClosed(1, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println(recursion);
        // 1 * 2 * 3 * 4 * 5 = 120
    }


    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    // tail-call optimization
    // Will not save the stack
    // f(1, 5) -> f(5, 4) -> f(20, 3) -> f(60, 2) -> f(120, 1) -> return
    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }
}
