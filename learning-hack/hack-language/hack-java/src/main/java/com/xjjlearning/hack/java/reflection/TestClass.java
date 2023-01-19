package com.xjjlearning.hack.java.reflection;

class TestClass {
    static class TestSub {
        static {
            System.out.println("init TestSub class..");
        }
    }

    static {
        // 不会执行
        System.out.println("initialize with static codes..");
    }

    private int n;

    public TestClass(int n) {
        // 不会执行
        System.out.println("initialize Test class");
        this.n = n;
    }

    public String intToString(int n) {
        return Integer.toString(n);
    }
}