package com.xjjlearning.java.util.function;

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