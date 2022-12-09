package com.xjjlearning.java.util.concurrent.chapter3;

class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;
        // loadload
        int j = v2;
        // loadstore
        a = i + j;
        // storestore
        v1 = i + 1;
        // storestore
        v2 = j * 2;
        // sotreload
    }
}
