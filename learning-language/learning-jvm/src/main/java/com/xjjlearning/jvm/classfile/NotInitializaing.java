package com.xjjlearning.jvm.classfile;

/**
 * created by xjj on 2023/1/16
 */
// 《深入理解java虚拟机》 7.2
class SuperClass {
    static {
        value = 2;
        System.out.println("Super class init");
    }
    public static int value = 1;
}
class SubClass extends SuperClass {
    static {
        System.out.println("Sub class init");
    }
}
class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORLD = "EvilTemplatesImpl World";
}

public class NotInitializaing {
    public static void main(String[] args) {
        // 只输出父类的初始化
        System.out.println(SubClass.value);

        // 没有初始化
        // SuperClass[] superClasses = new SuperClass[10];

        // const放入常量池后, ConstClass类已经没用了
        // System.out.println(ConstClass.HELLOWORLD);
    }
}

/**
 *
 */
