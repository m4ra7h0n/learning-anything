package com.xjjlearning.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // flux 0..n elements
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        Flux<Integer> range = Flux.range(5, 3);
        range.subscribe(System.out::println);


        // Mono 0/1 element
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        Mono<Long> count = Flux.range(5, 3).count();

    }
}
