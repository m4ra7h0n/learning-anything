package com.xjjlearning.reactor.core.springaction5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by xjj on 2022/12/7
 */
@SpringBootTest
public class SpringInAction5Chap10_3_2_Test {
    @Test
    void chapter10CreateFluxPublisher() {
        String[] strings = {"xjj1", "xjj2"};
        Flux<String> stringFlux = Flux.fromArray(strings);
        StepVerifier.create(stringFlux)
                .expectNext("xjj1")
                .expectNext("xjj2")
                .verifyComplete();

        List<String> l = Stream.of("xjj1", "xjj2").collect(Collectors.toList());
        Flux<String> stringFlux1 = Flux.fromIterable(l);
        StepVerifier.create(stringFlux1)
                .expectNext("xjj1")
                .expectNext("xjj2")
                .verifyComplete();

        Flux<String> stringFlux2 = Flux.fromStream(Stream.of("xjj1", "xjj2"));
        StepVerifier.create(stringFlux2)
                .expectNext("xjj1")
                .expectNext("xjj2")
                .verifyComplete();

        Flux<Integer> range = Flux.range(1, 3);
        StepVerifier.create(range)
                .expectNext(1, 2, 3)
                .verifyComplete();

        // send one per second, take 3
        Flux<Long> take = Flux.interval(Duration.ofSeconds(1)).take(3);
        StepVerifier.create(take)
                .expectNext(0L, 1L, 2L)
                .verifyComplete();
    }

    @Test
    void mergeFluxes() {
        Flux<String> flux1 = Flux.just("xjj1", "xjj2")
                .delayElements(Duration.ofMillis(500)); // send element each 0.5s
        Flux<String> flux2 = Flux.just("xjj3", "xjj4")
                .delaySubscription(Duration.ofMillis(250)) // wait for 0.25s
                .delayElements(Duration.ofMillis(500)); // send element each 0.5s

        Flux<String> flux3 = flux1.mergeWith(flux2);
        StepVerifier.create(flux3) // subscribe two fluxes (flux1, flux2)
                .expectNext("xjj1", "xjj3", "xjj2", "xjj4")
                .verifyComplete();
    }

    @Test
    public void zipFluxes() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa"/**, "xjj"**/);
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

        Flux<Tuple2<String, String>> zippedFlux = Flux.zip(characterFlux, foodFlux);

        StepVerifier.create(zippedFlux)
                .expectNextMatches(p ->
                        p.getT1().equals("Garfield") &&
                                p.getT2().equals("Lasagna"))
                .expectNextMatches(p ->
                        p.getT1().equals("Kojak") &&
                                p.getT2().equals("Lollipops"))
                .expectNextMatches(p ->
                        p.getT1().equals("Barbossa") &&
                                p.getT2().equals("Apples"))
                // .expectNextMatches(p -> p.getT1().equals("xjj"))
                .verifyComplete();
    }
    @Test
    void zipFluxsToObject() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa"/**, "xjj"**/);
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");
        // custom create zip method
        Flux<String> zip = Flux.zip(characterFlux, foodFlux, (a, b) -> a + " eats " + b);
        StepVerifier.create(zip)
                .expectNext("Garfield eats Lasagna")
                .expectNext("Kojak eats Lollipops")
                .expectNext("Barbossa eats Apples")
                .verifyComplete();
    }
    @Test
    void firstFlux() {
        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
                .delaySubscription(Duration.ofMillis(100));
        Flux<String> fastFlux = Flux.just("xjj");
        Flux<String> firstFlux = Flux.firstWithValue(fastFlux, slowFlux);
        StepVerifier.create(fastFlux)
                .expectNext("xjj")
                .verifyComplete();

    }

}
