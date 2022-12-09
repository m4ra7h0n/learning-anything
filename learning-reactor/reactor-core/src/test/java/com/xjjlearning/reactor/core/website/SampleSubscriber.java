package com.xjjlearning.reactor.core.website;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;
import reactor.util.annotation.NonNull;

// At least cover hookOnSubscribe() and hookOnNext()
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override // execute before first request
    protected void hookOnSubscribe(@NonNull Subscription subscription) {
        System.out.println("Subscribed and then request");
        //tell the upstream, I can request 1 element each time. back pressure !
        request(1);
        // super.hookOnSubscribe(subscription);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("hook on complete !");
        super.hookOnComplete();
    }

    @Override
    protected void hookOnError(@NonNull Throwable throwable) {
        System.out.println("hook on error !");
        super.hookOnError(throwable);
    }

    @Override
    protected void hookOnCancel() {
        System.out.println("hook on cancel !");
        super.hookOnCancel();
    }

    @Override
    protected void hookFinally(@NonNull SignalType type) {
        System.out.println("hook finally ! -> " + type);
        super.hookFinally(type);
    }

    @Override
    protected void hookOnNext(@NonNull T value) {
        System.out.println("hookOnNext " + value);
        // request(1);
        request(2);
        // super.hookOnNext(value);
    }

}
