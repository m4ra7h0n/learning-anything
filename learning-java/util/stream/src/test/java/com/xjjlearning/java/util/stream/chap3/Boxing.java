package com.xjjlearning.java.util.stream.chap3;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Boxing {
    public static void main(String[] args) {
        // int被包装成Integer, 消耗资源
        // int value was wrapped into Integer, use heap space
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add(i);
        }
        // unboxing
        IntPredicate evenNumbers = (int i) -> (i & 1) == 0;
        evenNumbers.test(1000);

        // boxing
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        oddNumbers.test(1000);
    }

    @FunctionalInterface
    public interface IntPredicate{
        boolean test(int t);
    }
}