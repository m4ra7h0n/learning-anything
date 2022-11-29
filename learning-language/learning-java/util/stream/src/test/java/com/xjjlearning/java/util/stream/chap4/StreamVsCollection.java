package com.xjjlearning.java.util.stream.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {

    public static void main(String...args){
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        Stream<String> s2 = s.map(i -> i + "i");
        Stream<Integer> s3 = s.map(String::length).filter(i -> i < 3);

        s2.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        // s3.forEach(System.out::println);
    }
}