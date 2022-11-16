package com.xjjlearning.java.util.concurrent.chapter3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReorderExampleTest {
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

    @Test
    public void reorderTest() {  // ? why always print 1
        List<Thread> l = new ArrayList<>();
        l.add(new Thread(this::writer));
        l.add(new Thread(this::reader));
        l.forEach(Thread::start);
    }
}