package com.xjjlearning.jvm.deeptoeasy;

/**
 * Created by xjj on 2023/3/4.
 */
public class SomethingFun {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        System.out.println(i); // 1 :)
        /*
         * L0
         *     ICONST_1 // 直接取常量（整数 1），压入栈中
         *     ISTORE 0 // 弹栈，并存入局部变量（Slot 0）
         * L1
         *     ILOAD 0 // 从局部变量（Slot 0）取值，压入栈中
         *     IINC 0 1 // 对局部变量（Slot 0）进行自增（+1）操作，与栈无关
         *     ISTORE 0 // 弹栈，并存入局部变量（Slot 0）
         */
    }
}
