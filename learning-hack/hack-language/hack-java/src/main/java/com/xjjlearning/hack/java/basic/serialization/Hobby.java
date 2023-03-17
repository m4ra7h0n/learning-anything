package com.xjjlearning.hack.java.basic.serialization;

import java.io.Serializable;

public class Hobby implements Serializable {
    String h[];
    int c = 1;// 4
    long d = 1L;// 8

    public Hobby(String... h) {
        this.h = h;
    }
}