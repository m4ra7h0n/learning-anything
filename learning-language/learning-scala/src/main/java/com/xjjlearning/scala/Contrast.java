package com.xjjlearning.scala;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

// where is the color ? :(
public class Contrast {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(set);
        // unmodifiableSet.add(1);

        Stream.of(1, 3, 5, 7)
                .map(multiplyCurry(2))
                .forEach(System.out::println);
    }

    static Function<Integer, Integer> multiplyCurry(int x) {
        return (Integer y) -> x * y;
    }

}
