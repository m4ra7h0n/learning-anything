package com.xjjlearning.java.util.function.functionalinterface;

import lombok.Data;

@Data
public class Fruit {
    int weight;
    String color;
    public Fruit(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
    public Fruit(int weight) { this.weight = weight; }
}

class Apple extends Fruit{
    public Apple(int weight, String color) { super(weight, color); }
    public Apple(int weight) { super(weight); }
}

class Orange extends Fruit{
    public Orange(int weight) { super(weight); }
    public Orange(int weight, String color) { super(weight, color); }
}
