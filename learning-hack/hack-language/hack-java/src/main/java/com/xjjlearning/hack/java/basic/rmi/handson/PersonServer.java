package com.xjjlearning.hack.java.basic.rmi.handson;

public class PersonServer implements Person {
    private final int age;
    private final String name;

    public PersonServer(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}     