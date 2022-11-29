package com.xjjlearning.java.util.stream.chap5;

import com.xjjlearning.java.util.stream.pojo.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FindTest {
    @Test
    public void findTest() {
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 7 == 0)
                        .findFirst(); // 9
        try {
            System.out.println(firstSquareDivisibleByThree.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
