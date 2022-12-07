package com.xjjlearning.reactor.core.website;

import com.xjjlearning.reactor.core.website.iface.MyEventListener;
import com.xjjlearning.reactor.core.website.iface.MyEventProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class FluxTest {
    @Test
    void fluxFactoryTest() {
        // flux 0..n elements
        // factory
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        StepVerifier.create(seq1)
                .expectNext("foo")
                .expectNext("bar")
                .expectNext("foobar")
                .verifyComplete();
    }

    @Test
    void fluxSubscribeTest() {
        // subscribe demo
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> subscriber = Flux.range(1, 4).map(i -> {
            if (i <= 3) return i;
            // throw new RuntimeException("Go to 4");
            return i + 1;
        });
        subscriber.subscribe( // add a subscriber
                System.out::println, // success
                e -> System.out.println("error: " + e),  //error
                () -> System.out.println("Done"),   //callback
                s -> ss.request(10) // self-defined subscriber
        );
        subscriber.subscribe(ss); // must add it

    }

    @Test
    public void fluxGenerateTest() {
        // learn sink -> FluxSink, MonoSink, SynchronousSink
        // Create a sequence
        // 1. generate based on state -> compare flux to stream
        System.out.println("====== Flux ======");
        Flux.generate(    // SynchronousSink
                () -> 0, // state can be mutable(such as AtomicInteger) or immutable
                (state, sink) -> {
                    // Publish elements downstream
                    sink.next("3 x " + state + " = " + state * 3);
                    // Ending the publishing element
                    if (state == 10) sink.complete();
                    return state + 1;
                    // clear the state, such as close the database connection
                }, state -> System.out.println("consume the ending value: " + state))
                .subscribe(System.out::println);

        System.out.println("====== Stream ======");
        Stream.iterate(0, i -> {
            System.out.println("3 x " + i + " = " + i * 3);
            return i + 1;
        }).limit(12).collect(Collectors.toList());
    }

    private final MyEventProcessor myEventProcessor = new MyEventProcessor() {
        MyEventListener<String> eventListener;
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        @Override
        public void register(MyEventListener<String> eventListener) {
            this.eventListener = eventListener;
        }

        @Override
        public void dataChunk(String... values) {
            executor.schedule(() ->eventListener.onDataChunk(Arrays.asList(values)),
                    500, TimeUnit.MICROSECONDS);
        }

        @Override
        public void processComplete() {
            executor.schedule(() -> eventListener.processComplete(),
                    500, TimeUnit.MICROSECONDS);
        }
    };

    @Test
    void fluxCreateTest() {
        Flux<String> bridge = Flux.create(sink -> { // FluxSink
            myEventProcessor.register( // <4>
                    new MyEventListener<>() { // <1>
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s); // <2>
                            }
                        }
                        public void processComplete() {
                            sink.complete(); // <3>
                        }
                    });
        }, FluxSink.OverflowStrategy.BUFFER).map(s -> s + "1");

        bridge.subscribe(System.out::println);

        myEventProcessor.dataChunk("foo", "bar", "xjj");
        myEventProcessor.processComplete();


        // StepVerifier.withVirtualTime(() -> bridge)
        //         .expectSubscription()
        //         .expectNoEvent(Duration.ofSeconds(10))
        //         .then(() -> myEventProcessor.dataChunk("foo", "bar", "baz"))
        //         .expectNext("foo", "bar", "baz")
        //         .expectNoEvent(Duration.ofSeconds(10))
        //         .then(() -> myEventProcessor.processComplete())
        //         .verifyComplete();
    }

    @Test
    void fluxPushTest() {
        Flux<String> bridge = Flux.push(sink -> { // FluxSink
            myEventProcessor.register( // <4>
                    new MyEventListener<>() { // <1>
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s); // <2>
                            }
                        }
                        public void processComplete() {
                            sink.complete(); // <3>
                        }
                    });
        }).map(s -> s + "1");

    }

    @Test
    public void monoTest() {
        // Mono 0/1 element
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        Mono<Long> count = Flux.range(5, 3).count();
    }
}
