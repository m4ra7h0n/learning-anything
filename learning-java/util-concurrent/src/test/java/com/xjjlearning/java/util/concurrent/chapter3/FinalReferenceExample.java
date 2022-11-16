package com.xjjlearning.java.util.concurrent.chapter3;

public class FinalReferenceExample {
    final int[]                  intArray; //
    static FinalReferenceExample obj;

    public FinalReferenceExample() { //
        intArray = new int[1]; //1
        intArray[0] = 1; //2
    }

    public static void writerOne() { //
        obj = new FinalReferenceExample(); //3
    }

    public static void writerTwo() { //
        obj.intArray[0] = 2; //4
    }

    public static void reader() { //
        if (obj != null) { //5
            int temp1 = obj.intArray[0]; //6
        }
    }
}