package com.xjjlearning.java.util.function.functionalinterface;

import com.xjjlearning.java.util.function.functionalinterface.iface.Dog;

class functionalInterfaceTest{
    public static void main(String[] args) {
        Dog<String> dog = (String food) -> {
            System.out.println("dog eat " + food + " :)");
            return true;
        };
        System.out.println(dog.eat("shit"));
    }
}