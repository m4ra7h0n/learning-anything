package com.xjjlearning.jvm.deeptoeasy;

import java.io.IOException;

/**
 * created by xjj on 2023/1/31
 */
public class StackOverFlow {
    // stack memory 1M and 8678 depth by default
    // try with -Xss256K then -> depth 1646

    public static int sigma(int n) {
        System.out.println("current 'n' value is " + n);
        return n + sigma(n + 1);
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> sigma(1))
                .start();
        System.in.read();
    }
}
