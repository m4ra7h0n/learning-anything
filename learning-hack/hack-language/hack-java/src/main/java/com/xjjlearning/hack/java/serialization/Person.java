package com.xjjlearning.hack.java.serialization;

import java.io.*;

/**
 * created by xjj on 2023/1/26
 */
public class Person implements Serializable {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject("This is a object");
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        String message = (String) s.readObject();
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age;
    }
}
