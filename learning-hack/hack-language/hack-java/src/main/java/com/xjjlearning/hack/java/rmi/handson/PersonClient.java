package com.xjjlearning.hack.java.rmi.handson;

public class PersonClient {
    public static void main(String[] args) {
        try {
            Person person = new Person_Stub();
            int age = person.getAge();
            String name = person.getName();
            System.out.println(name + " is " + age + " years old");
            // Thread.sleep(1000000000000L);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}  