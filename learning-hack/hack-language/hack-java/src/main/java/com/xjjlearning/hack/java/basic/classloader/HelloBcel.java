package com.xjjlearning.hack.java.basic.classloader;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.xjjlearning.hack.java.basic.classloader.bytecodes.EvilBcel;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;

import java.io.IOException;

/**
 * created by xjj on 2023/1/31
 */
// 8u251中移除 com.sun.org.apache.bcel.internal.util.ClassLoader
public class HelloBcel {
    private static String getEvilCode() throws IOException {
        // 或者使用这种方法
        // JavaClass javaClass = Repository.lookupClass(EvilTemplatesImpl.class);
        // byte[] code = javaClass.getBytes();

        byte[] code = ClassUtil.classAsBytes(EvilBcel.class);
        String encode = Utility.encode(code, true);
        System.out.println(encode);
        return encode;
    }

    public static void main(String[] args) throws Exception {
        // com.sun.org.apache.bcel.internal.util.ClassLoader 重写了loadClass方法

        // com.sun.org.apache.bcel.internal.util.ClassLoader#loadClass()
        //  com.sun.org.apache.bcel.internal.util.ClassLoader#createClass(); -> 解码字符串
        //  java.lang.ClassLoader#defineClass(); -> 生成类
        new ClassLoader().loadClass("$$BCEL$$" + getEvilCode()).newInstance();
    }
}
