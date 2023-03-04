package com.xjjlearning.jvm.deeptoeasy.classoperation;

import java.io.IOException;

public class RecursionAlgorithm {

    private static volatile int value = 0;

    // 局部变量表 -> 3 -> this, s1, s2
    public String s(String s1, String s2) {
        return "";
    }

    // 局部变量表 -> 1 -> n
    static int sigma(int n) {
        value = n;
        System.out.println("current 'n' value is " + n);
        return n + sigma(n + 1);
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> sigma(1)).start();
        System.in.read();
        System.out.println(value);
    }

}