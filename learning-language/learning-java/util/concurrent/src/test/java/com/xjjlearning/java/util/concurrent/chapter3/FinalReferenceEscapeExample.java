package com.xjjlearning.java.util.concurrent.chapter3;

public class FinalReferenceEscapeExample {

    final int                          i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1; //
        obj = this; //2
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        if (obj != null) { //3
            int temp = obj.i; //4
        }
    }

    public static void main(String[] args) {
        new Thread(FinalReferenceEscapeExample::writer).start();
        new Thread(FinalReferenceEscapeExample::reader).start();
    }
}