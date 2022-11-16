package com.xjjlearning.google.common;

import com.google.common.base.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        absentTry();
    }

    private static void presentTry() {
        Optional<Integer> possible = Optional.of(5); // throw exception if null
        System.out.println(possible.isPresent()); // returns true
        System.out.println(possible.get()); // returns 5
    }
    private static void absentTry() {
        Optional<Object> absent = Optional.absent();
        System.out.println(absent.get()); // throws exception
    }
}
