package com.xjjlearning.java.util.stream;

import com.xjjlearning.java.util.stream.collector.TolistCollector;
import com.xjjlearning.java.util.stream.pojo.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ToListCollectorTest {
    @Test
    public void myCollectorTest() {
        List<Dish> collect = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(new TolistCollector<>());
        System.out.println(collect);
    }
}
