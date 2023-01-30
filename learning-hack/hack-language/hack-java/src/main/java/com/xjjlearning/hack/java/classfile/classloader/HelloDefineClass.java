package com.xjjlearning.hack.java.classfile.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created by xjj on 2023/1/30
 */
public class HelloDefineClass {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass",
                String.class, byte[].class, int.class, int.class);
        defineClass.setAccessible(true);

        byte[] code = new byte[2048];
        Process exec = Runtime.getRuntime().exec("javac Hello.java");
        InputStream inputStream = exec.getInputStream();
        inputStream.read(code);
        // String s = Arrays.toString(Base64.getEncoder().encode(code));
        // System.out.println(s);

        // String name, byte[] b, int off, int len
        Class<?> hello = (Class<?>) defineClass.invoke(ClassLoader.getSystemClassLoader(), "Hello", code, 0, code.length);
        hello.newInstance();
    }
}
