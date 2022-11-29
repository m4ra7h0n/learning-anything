package com.xjjlearning.java.util.stream.chap5;

import com.xjjlearning.java.util.stream.pojo.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootTest
public class MappingTest {
    @Test
    public void mappingTest() {
        List<String> words = Arrays.asList("Hello", "World");

        // map将流中的当前元素替换为此返回值
        // map replaces this return value with the current element in the stream
        Stream<Stream<String>> mapStream = words.stream()
                .map(w -> w.split("")) // Stream<String> -> Stream<String[]>
                .map(Arrays::stream) // Stream<String[]> -> Stream<Stream<String>>
                .distinct();

        // 把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        // flatMap replaces each value in one stream with another, and then concatenates all the streams into a single stream
        Stream<String> flatMapStream = words.stream()
                .map(w -> w.split("")) // Stream<String> -> Stream<String[]>
                .flatMap(Arrays::stream) // Stream<String[]> -> Stream<String>
                .distinct();

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

    }

    @Test
    public void practice1() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer> numbers3 = Arrays.asList(5, 6, 7);
        // flatMap
        List<int[]> collect = numbers1.stream()
                .flatMap(i -> numbers2.stream() // Stream<int[]> -> Stream<Stream<Integer>> -> Stream<Integer>
                        .flatMap(j -> numbers3.stream()
                                .map(k -> new int[]{i, j, k}))) // Stream<Integer> -> Stream<int[]>
                .filter(pair -> (pair[0] + pair[1] + pair[2]) % 3 == 0)
                .collect(toList());
        collect.forEach((int[] n) -> {
            System.out.printf("(%d,%d,%d)%n", n[0], n[1], n[2]);
        });
    }
    @Test
    public void intMappingTest() {
        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> orignStream = intStream.boxed();

        OptionalInt max = Dish.menu.stream().mapToInt(Dish::getCalories).max();
        int i = max.orElse(1);
    }

    public static void main(String...args){

        // map
        List<String> dishNames = Dish.menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // flatMap
        words.stream()
                 .flatMap((String line) -> Arrays.stream(line.split("")))
                 .distinct()
                 .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

    }
}
