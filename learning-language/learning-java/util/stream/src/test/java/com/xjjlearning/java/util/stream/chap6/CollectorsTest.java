package com.xjjlearning.java.util.stream.chap6;

import com.xjjlearning.java.util.stream.pojo.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

@SpringBootTest
public class CollectorsTest {
    @Test
    public void summarizingTest() {
        /**
         *    Collectors.counting()/maxBy()/minBy() --> Collectors.reducing()
         *
         *    Stream.max()/min()/sum() --> ReferencePipeline.reduce()
         */

        // dish count
        Dish.menu.stream()
                .collect(counting());

        // max
        Dish.menu.stream()
                .collect(maxBy(comparingInt(Dish::getCalories)));
        Dish.menu.stream()
                .max(comparingInt(Dish::getCalories)); // max/sum -> reduce()

        // sum
        Dish.menu.stream()
                .collect(summingInt(Dish::getCalories));
        Dish.menu.stream()
                .mapToInt(Dish::getCalories).sum();
        Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        // avg
        Dish.menu.stream()
                .collect(averagingInt(Dish::getCalories));


        // all msg {count, sum, min, avg, max}
        IntSummaryStatistics collect = Dish.menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(collect);
        System.out.println(collect.getAverage());
    }


    @Test
    public void joinTest() {
        String collect = Dish.menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));
        System.out.println(collect);


        // all caloric
        Dish.menu.stream()
                .collect( reducing(0, Dish::getCalories, Integer::sum) ); //Integer
        Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (i, j) -> i + j); //Integer
        Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum(); //int


        // Equivalent to joining
        Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        Dish.menu.stream()
                .map(Dish::getName).reduce((o1, o2) -> o1 + ", " + o2);
        Dish.menu.stream()
                .map(Dish::getName)
                .collect(reducing((o1, o2) -> o1 + ", " + o2));

        // reducing -> BiOperation<T> -> BiFunction<T, T, T>. returns the same type(String)
        // Dish.menu.stream()
        //         .collect(reducing((o1, o2) -> o1.getName() + o2.getName()))

    }


    @Test
    public void groupingTest() {
        // groupBy normally
        Map<Dish.Type, Map<Object, List<Dish>>> collect = Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        })));
        System.out.println(collect);

        // mapping to set
        Map<Dish.Type, Set<Dish.CaloricLevel>> collectMapping = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        }, toSet()
                )));
        System.out.println(collectMapping);

        // mapping to Hashset by toCollection() method
        Map<Dish.Type, Set<Dish.CaloricLevel>> collectMapping2 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        }, toCollection(HashSet::new)
                )));
        System.out.println(collectMapping2);


        // groupBy and count it as the result
        Map<Dish.Type, Long> collect1 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, counting())); //count the result as the final result
        System.out.println(collect1);

        // groupBy and sum the caloric as the rsult
        Map<Dish.Type, Integer> collect2 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(collect2);

        // groupBy and select the max one as the result
        Map<Dish.Type, Dish> collect3 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)), Optional::get
                )));
        System.out.println(collect3);
    }

    @Test
    public void partitionTest() {
        // partition
        Map<Boolean, List<Dish>> partition = Dish.menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        List<Dish> dishes = partition.get(true);
        List<Dish> dishes1 = Dish.menu.stream()
                .filter(Dish::isVegetarian) // true
                .collect(toList());
        System.out.println(dishes);
        System.out.println(dishes1);


        // Vegetarian and non-vegetarian for the highest number of calories
        Map<Boolean, Dish> collect4 = Dish.menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get))); //downstage
        System.out.println(collect4); // pork, pizza


    }






}
