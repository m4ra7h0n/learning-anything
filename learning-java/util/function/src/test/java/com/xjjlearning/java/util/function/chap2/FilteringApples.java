package com.xjjlearning.java.util.function.chap2;

import com.xjjlearning.java.util.function.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> apples = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        apples.forEach(System.out::println);

    }

    public static <T> List<T> filterApples(List<T> inventory, ApplePredicate<T> p) {
        List<T> apples = new ArrayList<>();
        for (T apple : inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    interface ApplePredicate<T> {
        boolean test(T a);
    }
}
