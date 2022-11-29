package com.xjjlearning.java.util.stream.chap10;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OptionalTest {
    Properties props = new Properties();
    @Test
    public void optinalTest() {
        Optional<String> optional1 = Optional.of("abc");
        Optional<Integer> optinal2 = optional1.map(String::length);

        assertEquals(optinal2, optional1);

        int i = Integer.parseInt("13", 20);

        assertEquals(1, 2);
    }

    @Test
    public void propertyTest() {
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(5, readDuration(props, "a"));
        assertEquals(0, readDuration(props, "b"));
        assertEquals(0, readDuration(props, "c"));
        assertEquals(0, readDuration(props, "d"));
    }
    public int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalTest::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
