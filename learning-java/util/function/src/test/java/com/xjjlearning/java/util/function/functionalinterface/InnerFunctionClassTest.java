package com.xjjlearning.java.util.function.functionalinterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootTest
public class InnerFunctionClassTest {
    @Test
    public void predicateTest() { // one arg, return boolean
        Predicate<String> stringPredicate = o -> !o.isEmpty() && o.trim().length() == 0;
        boolean test = stringPredicate.test(" ");
        System.out.println(test);
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
    }

    @Test
    public void consumerAndThenTest() {
        Consumer<String> lengthConsumer = s -> System.out.println(s.length());
        Consumer<String> printConsumer = lengthConsumer.andThen(System.out::println);
        printConsumer.accept("xjj");
    }

    @Test
    public void functionTest() { // one arg, one result
        Function<String, Integer> stringFunction = (o) -> o.length(); //in -> String. out -> Integer
        Function<String, Integer> lambda = String::length;

        System.out.println(stringFunction.apply("123"));
    }

    @Test
    public void supplierTest() { // no arg, one result
        Supplier<String> a = () -> "val";
        System.out.println(a.get());
    }
}
