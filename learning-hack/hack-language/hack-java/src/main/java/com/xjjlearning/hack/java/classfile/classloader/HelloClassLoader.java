package com.xjjlearning.hack.java.classfile.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * created by xjj on 2023/1/30
 */
public class HelloClassLoader {
    private void loadClass() throws Exception{
        URL[] urls = {new URL("http://47.95.7.37:9870/greet/")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class<?> clazz = classLoader.loadClass("Hello");
        clazz.newInstance();
    }

    public static void main(String[] args) throws Exception {
        new HelloClassLoader().loadClass();
    }
}
