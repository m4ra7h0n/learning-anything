package com.xjjlearning.hack.java;

import groovy.lang.GroovyShell;
import org.junit.Test;

/**
 * Created by xjj on 2023/3/17.
 */
public class GroovyShellTest {
    @Test
    public void f() {
        GroovyShell groovyShell = new GroovyShell();
//        groovyShell.evaluate("'calc'.execute()");
        groovyShell.evaluate("System.out.println('hello world')");
    }
}
