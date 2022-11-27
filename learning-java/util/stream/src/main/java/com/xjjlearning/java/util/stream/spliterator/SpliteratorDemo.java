package com.xjjlearning.java.util.stream.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorDemo<T> implements Spliterator<T> {
    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
