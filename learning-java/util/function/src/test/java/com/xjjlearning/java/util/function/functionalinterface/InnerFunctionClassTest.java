package com.xjjlearning.java.util.function.functionalinterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.*;

@SpringBootTest
public class InnerFunctionClassTest {
    /**
     *     Predicate
     */
    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        // return inventory.stream()
        // .filter(p)
        // .collect(Collectors.toList());
        List<T> res = new ArrayList<>();
        for (T t : inventory) {
            if (p.test(t))
                res.add(t);
        }
        return res;
    }
    @Test
    public void predicateTest() { // one arg, return boolean
        Predicate<String> stringPredicate = o -> !o.isEmpty() && o.trim().length() == 0;
        boolean test = stringPredicate.test(" ");
        System.out.println(test);

        filter(Arrays.asList(1, 2, 3, 4), i -> i <= 3);

        Predicate<Apple> redApple = (Apple a) -> a.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redHeavyAppleOrGreen = redApple
                // priority from left to right
                // (redApple and weight > 150) or color == green
                .and(a -> a.getWeight() > 150)
                .or(a -> a.getColor().equals("green"));
    }

    /**
     *   Consumer
     */
    public static <T> void forEach(List<T> inventory, Consumer<T> c) {
        // inventory.forEach(c);
        for (T t : inventory) {
            c.accept(t);
        }
    }
    @Test
    public void consumerAcceptTest() { // one arg, return void
        // base
        Consumer<String> stringConsumer = new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        };

        // lambda
        Consumer<String> lengthConsumer = s -> System.out.println(s.length());
        lengthConsumer.accept("xjj");

        // lambda
        Consumer<String> printConsumer = System.out::println;
        printConsumer.accept("xjj");

        forEach(Arrays.asList(1, 2, 3), i -> System.out.println(i));
    }

    @Test
    public void consumerAndThenTest() {
        Consumer<String> lengthConsumer = s -> System.out.println(s.length());
        Consumer<String> printConsumer = lengthConsumer.andThen(System.out::println);
        printConsumer.accept("xjj");

        List<Integer> map = map(Arrays.asList(1, 2, 3), (i -> i * 2));
    }


    /**
            Function
     **/
    public static <T, R> List<R> map(List<T> inventory, Function<T, R> f) { //in -> T. out -> R
        List<R> res = new ArrayList<>();
        for (T t : inventory) {
            res.add(f.apply(t));
        }
        return res;
    }

    Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    Fruit giveMeFruit (String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

    @Test
    public void functionTest() { // one arg, one result
        Function<String, Integer> stringFunction = (o) -> o.length(); //in -> String. out -> Integer
        Function<String, Integer> lambda = String::length;

        System.out.println(stringFunction.apply("123")); // 3


        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
        Fruit apple = giveMeFruit("apple", 1);

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * x;
        Function<Integer, Integer> f3 = f2.andThen(f1); // f3 = f1(f2(x))
        Function<Integer, Integer> f4 = f2.compose(f1); // f4 = f2(f1(x))
        System.out.println(f3.apply(2)); // (2 * 2) + 1 -> 5
        System.out.println(f4.apply(2)); // (2 + 1) * (2 + 1) -> 9
    }

    @Test
    public void biFunctionTest() {
        BiFunction<Integer, String, Apple> f = Apple::new;
        Apple greenApple = f.apply(1, "green");
        System.out.println(greenApple.getWeight());
        // TriFunction -> 3 args, 1 return
    }


    /**
     *  Supplier
     */
    @Test
    public void supplierTest() { // no arg, one result
        Supplier<String> a = () -> "val";
        System.out.println(a.get());
    }
}
