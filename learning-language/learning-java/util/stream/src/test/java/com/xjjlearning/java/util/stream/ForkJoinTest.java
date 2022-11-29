package com.xjjlearning.java.util.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@SpringBootTest
public class ForkJoinTest {
    @Test
    public void forkJoinTest() {
        // CountedCompleter
        // RecursiveAction
        // RecursiveTask
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        forkJoinPool.invoke(new ForkJoinTask<>() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        });
        forkJoinPool.execute(() -> System.out.println(1));
    }
    @Test
    public void otherTest() {
        List<String> list = Arrays.asList("123", "45634", "7892", "abch", "sdfhrthj", "mvkd");
        list.stream()
                .map(i -> i + "")
                .filter(i -> i.length() > 5)
                .forEach(System.out::println);
    }
}

