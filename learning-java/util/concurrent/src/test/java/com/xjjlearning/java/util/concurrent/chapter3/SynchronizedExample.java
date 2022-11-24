package com.xjjlearning.java.util.concurrent.chapter3;

class SynchronizedExample {
    int     a    = 0;
    boolean flag = false;

    public synchronized void writer() { // get lock
        a = 1;
        flag = true;
    } // release lock

    public synchronized void reader() { // get lock
        if (flag) {
            int i = a;
            System.out.println(i);
        } // release lock
    }
}
