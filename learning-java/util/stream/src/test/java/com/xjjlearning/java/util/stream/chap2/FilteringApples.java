package com.xjjlearning.java.util.stream.chap2;

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


    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
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
