package com.xjjlearning.java.util.concurrent.chapter3;

class ReorderExample {
    int     a    = 0;
    boolean flag = false;

    // thread-1
    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    // thread-2
    public void reader() throws RuntimeException {
        if (flag) { //3
            int i = a * a; //4
        } else {
            throw new RuntimeException("reordered !");
        }
    }
}