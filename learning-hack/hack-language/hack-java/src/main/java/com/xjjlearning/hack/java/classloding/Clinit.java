package com.xjjlearning.hack.java.classloding;

/**
 * created by xjj on 2023/1/17
 */
// clinit是class类构造器对静态变量，静态代码块进行初始化
// https://blog.csdn.net/u013309870/article/details/72975536
public class Clinit {
    static class Parent {
        public Parent() {
            // <init>
        }

        public static int A = 1;
        static {
            // <clinit>
            A = 2;
        }
    }
    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
