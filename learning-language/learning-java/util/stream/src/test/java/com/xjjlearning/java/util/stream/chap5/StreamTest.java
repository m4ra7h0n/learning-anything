package com.xjjlearning.java.util.stream.chap5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {
    @Test
    public void intStreamTest() {
        IntStream range = IntStream.range(0, 100)
                        .filter(i -> (i & 1) == 0);
        range.forEach(System.out::println);

        IntStream.rangeClosed(0, 100)
                .map(i -> i + 2) // int -> int
                .forEach(System.out::println);
    }
    @Test
    public void streamTest() {
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("src/test/java/com/xjjlearning/java/util/stream/chap5/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                                .distinct()
                                .count();
        } catch(IOException ignored){
        }

        Stream<Integer> iterate = Stream.iterate(0, i -> i + 2);
        iterate.limit(3)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}) // febonaqi
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        // Use the given supply source and call the getAsInt method repeatedly
        Stream.generate(Math::random) // ????....
                .limit(5)
                .forEach(System.out::println);

        Stream.generate(() -> 1)  // 11111111...
                .limit(5)
                .forEach(System.out::println);

        // save the status. it's not best in lambda method. and not supported in parallelStream
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
