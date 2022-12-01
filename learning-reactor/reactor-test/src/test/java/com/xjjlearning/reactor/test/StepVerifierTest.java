package com.xjjlearning.reactor.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListenerMethodProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class StepVerifierTest {

    @Test
    public void AppendBoomErrorTest() {
        EventListenerMethodProcessor eventListenerMethodProcessor = new EventListenerMethodProcessor();
        Flux<String> source = Flux.just("foo", "bar");
        StepVerifier.create(appendBoomError(source))
                .expectNext("foo")
                .expectNext("bar")
                .expectErrorMessage("boom")
                .verify();
    }
    public <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }

}
