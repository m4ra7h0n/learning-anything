package com.xjjlearning.reactor.core.springaction5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * created by xjj on 2022/12/7
 */
@SpringBootTest
public class SpringInAction5Chap10_3_4_Test {
    @Test
    void all() {
        Flux<String> animalFlux = Flux.just(
                "aardvark", "elephant", "koala", "eagle", "kangaroo");
        Mono<Boolean> a = animalFlux.all(w -> w.contains("a"));
        Mono<Boolean> z = animalFlux.any(w -> w.contains("z"));
        StepVerifier.create(a)
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(z)
                .expectNext(false)
                .verifyComplete();
    }
}
