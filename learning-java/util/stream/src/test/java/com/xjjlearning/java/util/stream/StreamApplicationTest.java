package com.xjjlearning.java.util.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StreamApplicationTest {
    // learn how to use
    // https://developer.aliyun.com/article/903870

    @Test
    public void streamTest() {
        // that's ok, debug fluently
        // 1.create head stage, map stage, filter stage. and linked them as a 'ReferencePipeline' linkedlist
        // 2.when execute to forEach, wrap the stage to wrapStage. create downstream to a sink list
        // Sink.ChainedReference(map) --downstream--> Sink.ChainedReference(filter) --downstream--> ForEachOp.OfRet
        // 3.call the Sink#accept() method from head to tail in sequence
        Stream.of("java", "scala", "go", "python")
                .map(String::length)
                .filter(i -> i <= 4)
                .forEach(System.out::println);

        // not easy to debug into filter accept method
        Stream<Integer> lanStream = Stream.of("java", "scala", "go", "python")
                .map(String::length)
                .filter(i -> i <= 4);
        lanStream.forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 1, 4, 4, 5, 6, 7, 8, 8, 9, 10, 11);
        Stream<Integer> limit = integers.stream()
                .filter(t -> t > 5) //6 7 8 8 9 10 11
                .distinct() //6 7 8 9 10 11
                .skip(2) //8 9 10 11
                .limit(3); //8 9 10
        limit.forEach(System.out::println);
    }

    @Test
    public void splitIteratorTest() {
        // Spliterators.ArraySpliterator;
//         ArrayList.ArrayListSpliterator;
    }


    @Data
    @AllArgsConstructor
    static class Employee {
        private String name;
    }

    @Test
    public void mapTest() {
        // example 1
        List<Integer> integers = Arrays.asList(1, 1, 4, 4, 5, 6, 7, 8, 8, 9, 10, 11);
        Stream<Integer> integerStream = integers.stream()
                .map(i -> i * 2);
        integerStream.forEach(System.out::println);

        // example 2
        List<Employee> employees = Arrays.asList(
                new Employee("张三"),
                new Employee("李四"),
                new Employee("王五"),
                new Employee("赵六"),
                new Employee("田七")
        );

        List<String> collect = employees.stream() //return Head
                .map(Employee::getName) //return StatelessOp
                .collect(Collectors.toList());// call Methods in Collectors class
        System.out.println(collect);

        // mapToInt
        List<String> cc = Arrays.asList("a,a", "b,b", "cc", "d,d");
        cc.stream()
                .mapToInt(String::length)
                .forEach(System.out::println);


        // flatMap
        List<String> list = Arrays.asList("a,a=","b,b=","cc=","d,d=");
        System.out.println(
                list.stream()
                .flatMap(str -> {
                    String[] split = str.split(",");
                    return Arrays.stream(split);
                })
                .collect(Collectors.toList())
        );
    }

    @Test
    public void sortedTest() {
        List<String> list = Arrays.asList("bb","cc","ee","aa");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

}
