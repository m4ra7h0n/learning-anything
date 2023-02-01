package com.xjjlearning.jvm.classfile;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * created by xjj on 2023/1/30
 */

// top-level class
public class BasicCommand {
    static class NestedClass {
        class InnerClass {
            void m() {}
            class LocalClass {
                // anonymous class
            }
        }
    }

    public static void main(String[] args) {
        // getName
        System.out.println("-------getName------");
        System.out.println(String.class.getName());
        System.out.println(byte.class.getName());
        System.out.println(Object[].class.getName());
        System.out.println(int[][][][][][][].class.getName());

        // getSimpleName
        System.out.println("-------getSimpleName------");
        System.out.println(String.class.getSimpleName());
        System.out.println(byte.class.getSimpleName());
        System.out.println(Object[].class.getSimpleName());
        System.out.println(int[][][][][][][].class.getSimpleName());

        // getEnclosingClass
        System.out.println("------getEnclosingClass--------");
        System.out.println(new Object(){}.getClass().getEnclosingClass().getName()); // anonymous class
        System.out.println(String.class.getEnclosingClass()); // top-level class
        System.out.println(NestedClass.class.getEnclosingClass()); // class com.xjjlearning.hack.java.classfile.BasicCommand
        System.out.println(InnerClass.class.getEnclosingClass()); // null
        // class com.xjjlearning.hack.java.classfile.BasicCommand$NestedClass$InnerClass
        System.out.println(NestedClass.InnerClass.LocalClass.class.getEnclosingClass());
    }
}
