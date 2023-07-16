package com.xjjlearning.hack.snakeyaml;

public class User {

    String name;
    int age;

    public User() {
        System.out.println("User构造函数");
    }

    public String getName() {
        System.out.println("User.getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("User.setName");
        this.name = name;
    }

    public int getAge() {
        System.out.println("User.getAge");
        return age;
    }

    public void setAge(int age) {
        System.out.println("User.setAge");
        this.age = age;
    }

}