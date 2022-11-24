package com.xjjlearning.java.util.function.functionalinterface.iface;

@FunctionalInterface
public interface Dog<T> {
    // only one declared method
    boolean eat(T food);

    default Dog<T> and(T food) {
        return (T) -> eat(food);
    }
}