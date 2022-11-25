package com.xjjlearning.java.util.function.functionalinterface.iface;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface // the annotation is not must, just like @Override, but it's better to add it.
public interface Dog<T> {
    // only one declared abstract method
    boolean eat(T food);
    
    // Object Method
    boolean equals(Object obj);
    // default method
    default Dog<T> and(T food) {
        return (T) -> eat(food);
    }
    // Comparator Method
    public static <T, U> Comparator<T> comparing (
            Function<? super T, ? extends U> keyExtractor,
            Comparator<? super U> keyComparator)
    {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyComparator.compare(keyExtractor.apply(c1),
                        keyExtractor.apply(c2));
    }
}