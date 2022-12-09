package com.xjjlearning.reactor.core.springaction5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static reactor.core.scheduler.Schedulers.parallel;

/**
 * created by xjj on 2022/12/7
 */
@SpringBootTest
public class SpringInAction5Chap10_3_3_Test {
    @Test
    void skipAFiewElements() {
        Flux<String> skipFlux = Flux.just(
                        "one", "two", "skip a few", "ninety nine", "one hundred")
                .skip(3);
        StepVerifier.create(skipFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }

    @Test
    void skipAFiewSeconds() {
        Flux<String> skipFlux = Flux.just(
                        "one", "two", "skip a few", "ninety nine", "one hundred")
                // before first element, time stop 1 sec, ok
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(4));
        StepVerifier.create(skipFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }

    @Test
    void take() {
        Flux<String> skipFlux = Flux.just(
                        "one", "two", "skip a few", "ninety nine", "one hundred")
                .take(1);
        StepVerifier.create(skipFlux)
                .expectNext("one")
                .verifyComplete();

        Flux<String> skipFluxTime = Flux.just(
                        "one", "two", "skip a few", "ninety nine", "one hundred")
                .delayElements(Duration.ofSeconds(1))
                .take(Duration.ofMillis(3500));
        StepVerifier.create(skipFluxTime)
                .expectNext("one", "two", "skip a few")
                .verifyComplete();
    }


    @Test
    void filter() {
        Flux<String> nationalParkFlux = Flux.just(
                        "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
                .filter(np -> !np.contains(" "));
        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone", "Yosemite", "Zion")
                .verifyComplete();
    }


    @Test
    public void distinct() {
        Flux<String> animalFlux = Flux.just(
                        "dog", "cat", "bird", "dog", "bird", "anteater")
                .distinct();

        StepVerifier.create(animalFlux)
                .expectNext("dog", "cat", "bird", "anteater")
                .verifyComplete();
    }


    @Test
    void map() {
        Flux<String> m = Flux.just("1", "2")
                .map(i -> i + "ok");
        StepVerifier.create(m)
                .expectNext("1ok", "2ok")
                .verifyComplete();
    }

    private static class Player {
        String s1, s2;
        public Player(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }
    @Test
    void flatMap() {
        Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
                .flatMap(n -> // Divide and then merge
                        Mono.just(n) // create a Mono
                                .map(i -> {
                                    String[] split = i.split(" ");
                                    return new Player(split[0], split[1]);
                                })
                                /**
                                 * .immediate() * 在当前线程中执行订阅
                                 * .single()    * 在单个可重用线程中执行订阅，对所有调用方重复使用同一线程
                                 * .newSingle() * 在每个调用专用线程中执行订阅
                                 * .elastic()   * 在从无限弹性池中提取的工作进程中执行订阅，根据需要创建新的工作线程，并释放空闲的工作线程（默认情况下 60 秒）
                                 * .parallel()  * 在从固定大小的池中提取的工作进程中执行订阅，该池的大小取决于 CPU 核心的数量。
                                 */
                                // Indicates that each subscriber should take place in a parallel thread
                                // just like parallel() method in java Stream, it's a statement.
                                .subscribeOn(parallel()) // Order not guaranteed
                );

        playerFlux.subscribe(i -> System.out.println(i.s1 + " " + i.s2));
    }

    @Test
    void buffer() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> buffer = fruitFlux.buffer(3);

        buffer.subscribe(i -> {
            System.out.println(i.stream().collect(joining(",")));
        } );

        Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
                .buffer(3)
                .flatMap(x ->
                        Flux.fromIterable(x)
                                .map(String::toUpperCase)
                                .subscribeOn(parallel())
                                .log())
                .subscribe();

    }

    @Test
    void fluxToList() {
        Flux<String> fruitFlux = Flux.just(
                "apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> fruitFluxList = fruitFlux.buffer();
        Mono<List<String>> fruitListMono = fruitFlux.collectList();
        StepVerifier
                .create(fruitListMono)
                .expectNext(Arrays.asList(
                        "apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    void collectMap() {
        Flux<String> animalFlux = Flux.just(
                "aardvark", "elephant", "koala", "eagle", "kangaroo");
        Mono<Map<Character, String>> mapMono = animalFlux.collectMap(a -> a.charAt(0));
        StepVerifier.create(mapMono)
                .expectNextMatches(map -> map.size() == 3 &&
                        map.get('a').equals("aardvark") && map.get('k').equals("kangaroo"))
                .verifyComplete();
    }

}
