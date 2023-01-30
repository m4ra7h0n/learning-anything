package com.xjjlearning.hack.java.ysoserial.payloads.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * created by xjj on 2023/1/30
 */
public class ClassUtil {
    public static String classAsFile(final Class<?> clazz) {
        return classAsFile(clazz, true);
    }

    private static String classAsFile(Class<?> clazz, boolean suffix) {
        String str;
        if (clazz.getEnclosingClass() == null) {
            str = clazz.getName().replace(".", "/");
        } else {
            // 由于类中的类自己不能形成class文件所以要找外围类, 并加入$符号
            // There are five kinds of classes (or interfaces):
            // a) Top level classes
            // b) Nested classes (static member classes)
            // c) Inner classes (non-static member classes)
            // d) Local classes (named classes declared within a method)
            // e) Anonymous classes
            str = classAsFile(clazz.getEnclosingClass(), false) + "$" + clazz.getSimpleName();
        }
        if (suffix) {
            str += ".class";
        }
        return str;
    }

    public static byte[] classAsBytes(final Class<?> clazz) {
        try {
            final byte[] buffer = new byte[1024];
            final String file = classAsFile(clazz);
            final InputStream in = ClassUtil.class.getClassLoader().getResourceAsStream(file);
            if (in == null) {
                throw new IOException("couldn't find '" + file + "'");
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
