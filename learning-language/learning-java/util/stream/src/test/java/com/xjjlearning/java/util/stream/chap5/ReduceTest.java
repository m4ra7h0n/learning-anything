package com.xjjlearning.java.util.stream.chap5;

import com.xjjlearning.java.util.stream.pojo.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReduceTest {
    @Test
    public void reduceTest() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = nums.stream()
                .reduce(0, Integer::sum);

        Optional<Integer> sum = nums.stream() .reduce(Integer::sum);
        System.out.println(sum.get());

        Optional<Integer> max = nums.stream().reduce(Integer::max);
        System.out.println(max.get());

        // size
        int size = Dish.menu.size();
        Integer size2 = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        long size3 = Dish.menu.stream().count();
        System.out.printf("size: %d,%d,%d", size, size2, size3);
    }
}
