package com.xjjlearning.java.util.concurrent.chapter3;

public class VolatileFeaturesExample {
    volatile long vl = 0L;  //use volatile to declare 64-bit variables

    public void set(long l) {
        vl = l; // write volatile
    }

    public void getAndIncrement() {
        vl++; // read & write volatile
    }

    public long get() {
        return vl; // read volatile
    }
}

class VolatileFeaturesEquals {
    long vl = 0L;
    public synchronized void set(long l) {
        vl = l;
    }
    public void getAndIncrement() {
        long l = get();
        l += 1L;
        set(l);
    }
    public synchronized long get() {
        return vl;
    }
}
