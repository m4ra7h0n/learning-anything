package com.xjjlearning.java.util.stream.chap5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams{

    public static void main(String[] args) {
        Stream<int[]> pythagoreanTriples =
               IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                           .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed() // if not use boxed the map method can only return int value (IntStream)
                                           .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                                        // .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0)
                        );

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
}
