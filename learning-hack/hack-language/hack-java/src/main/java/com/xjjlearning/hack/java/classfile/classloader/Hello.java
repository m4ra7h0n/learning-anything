package com.xjjlearning.hack.java.classfile.classloader;

/**
 * created by xjj on 2023/1/30
 */

// 将此文件放到远端服务器上
public class Hello {
    static {
        System.out.println("hello, xjj");
    }
    public static void main(String[] args) {
        new Hello();
    }
}
