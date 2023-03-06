package com.xjjlearning.jvm.deeptoeasy.loadclass.jninative;

public class HelloNativeTest {
    static {
        // 此处不需要 dll/so 后缀，系统会自动根据系统不同换后缀
        System.loadLibrary("HelloNative");
    }

    public static void main(String[] args){
        HelloNative.greeting();
    }
}