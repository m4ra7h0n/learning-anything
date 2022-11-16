package com.xjjlearning.java.util.concurrent.chapter4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@SpringBootTest
public class MultiThreadTest {

    @Test
    public void multiThreadTest() {
        // java thread management -> MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // You do not need to get synchronized monitor and synchronizer information,
        // only thread and thread stack information
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // threadId threadName
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}