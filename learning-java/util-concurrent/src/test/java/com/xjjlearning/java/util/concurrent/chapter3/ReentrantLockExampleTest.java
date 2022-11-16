package com.xjjlearning.java.util.concurrent.chapter3;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExampleTest {
    int           a    = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock(); //get lock
        try {
            a++;
        } finally {
            lock.unlock(); //release lock
        }
    }

    public void reader() {
        lock.lock(); //get lock
        try {
            int i = a;
            //...
        } finally {
            lock.unlock(); //release lock
        }
    }
}
