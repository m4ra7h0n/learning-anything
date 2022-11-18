package com.xjjlearning.learningalgorithm;

public class Str {

    public int calculate(String s) {
        return -1;
    }

    private int StrToInt(String s) {
        // "123" -> 123
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            n = n * 10 + (c - '0');
        }
        return n;
    }
}
