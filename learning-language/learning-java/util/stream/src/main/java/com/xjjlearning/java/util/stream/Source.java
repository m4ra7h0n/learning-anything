package com.xjjlearning.java.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

public class Source {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Spliterator.OfInt spliterator = Spliterators.spliterator(nums, 0);
        Stream<Integer> stream = StreamSupport.stream(spliterator, false);

        List<String> names = Arrays.asList("kotlin", "java", "java", "go", "Scala", "Haskell");
        names.stream()
                .map(String::length)
                .filter(length -> length <= 4)
                .sorted()
                .distinct()
                .limit(4)
                .forEach(w -> System.out.println("language -> " + w));

        names.stream()
                .map(String::length)
                .filter(length -> length <= 4)
                .reduce((i, j) -> i + j + 1);
        names.stream()
                .map(String::length)
                .filter(length -> length <= 4)
                .reduce(1L, (i, j) -> i + 1, Long::sum);

        names.stream()
                .map(String::length)
                .filter(length -> length <= 4)
                .collect(Collectors.toList());

        names.stream()
                .map(String::length)
                .anyMatch(length -> length == 4);

        names.stream()
                .map(String::length)
                .findFirst();


        names.stream()
                .map(String::length)
                .collect(groupingBy(i -> i % 3 == 0,
                        groupingBy(j -> j % 5 == 0)));
    }
}
