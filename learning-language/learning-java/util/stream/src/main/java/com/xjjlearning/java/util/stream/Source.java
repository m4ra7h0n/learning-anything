package com.xjjlearning.java.util.stream;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Source {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Spliterator.OfInt spliterator = Spliterators.spliterator(nums, 0);
        Stream<Integer> stream = StreamSupport.stream(spliterator, false);

        List<String> names = Arrays.asList("kotlin", "java", "go");
        int maxLength = names.stream()
                .filter(name -> name.length() <= 4)
                .map(String::length)
                .max(Comparator.naturalOrder()).orElse(-1);
        System.out.println(maxLength);
    }
}
