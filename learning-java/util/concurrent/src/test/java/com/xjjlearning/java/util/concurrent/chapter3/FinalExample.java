package com.xjjlearning.java.util.concurrent.chapter3;

public class FinalExample {
    int                 i;  //normal
    final int           j;  //final
    static FinalExample obj;

    public FinalExample() { //construct
        i = 1; //write normal
        j = 2; //write final
        // StoreStore
    }

    public static void writer() { //thread-
        obj = new FinalExample();
    }

    public static void reader() { //thread-B
        FinalExample object = obj; //read reference
        int a = object.i; //read normal (maybe have not constructed)
        // LoadLoad
        int b = object.j; //read final  (object must be constructed)
    }
}