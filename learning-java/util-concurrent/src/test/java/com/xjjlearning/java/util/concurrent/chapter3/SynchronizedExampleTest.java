package com.xjjlearning.java.util.concurrent.chapter3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SynchronizedExampleTest {
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
    @Test
    public void synchronizedTest() {
        new Thread(this::writer).start();
        new Thread(this::reader).start();
    }
}
