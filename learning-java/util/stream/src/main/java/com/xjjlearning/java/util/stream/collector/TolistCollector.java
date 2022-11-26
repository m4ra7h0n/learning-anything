package com.xjjlearning.java.util.stream.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

// just like toList() factory, but this is a self-defined Class
public class TolistCollector<T> implements Collector<T, List<T>, List<T>> {//in -> T, res -> List<T>, mid -> ?
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        // return i -> i;
        return Function.identity();
    }


    // up part is enough
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> { left.addAll(right); return left; };
    }


    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH,
                CONCURRENT));
    }
}
