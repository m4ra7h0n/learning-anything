package com.xjjlearning.springframework.boot.annotation.functionalinterface;

import java.util.function.Consumer;

@FunctionalInterface
public interface Dog {
    public boolean eat(String food);
}

class functionalInterfaceTest{
    public static void main(String[] args) {
        Dog dog = (String food) -> {
            System.out.println("dog eat " + food + " :)");
            return true;
        };
        System.out.println(dog.eat("shit"));
    }
}