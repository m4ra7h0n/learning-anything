package com.xjjlearning.hack.java.serialization;

import java.io.*;
import java.util.Arrays;

/**
 * created by xjj on 2023/1/26
 */
public class GenerateSerializable {

    private String string;

    transient String name = "hello";

    private final static String dir = System.getProperty("user.dir") +
            "/learning-hack/hack-language/hack-java/src/main/resources/serialization";

    private static class ObjectOutputStreamTest extends ObjectOutputStream {
        static {
            // 学一下源码中的安全审计部分, 如果没有sm的话源码中会直接返回
            if (System.getSecurityManager() == null) {
                // System.setSecurityManager(new SecurityManager());
            }

            // 安全检查中不允许重写如下两个方法: putFields writeUnshared, 否则会发出 access denied 错误
            // 这两个方法会引发什么安全问题?
            // 我知道 putFields 方法被重写会导致反射相关问题, 我们可以把安全检查关闭试一下重写这个方法构造恶意方法
            // writeUnshared呢? 他是写入相同对象开辟新内存, 可能恶意重写会导致溢出
        }
        @Override
        protected void annotateClass(Class<?> cl) {
            // 序列化的时候 打印Person类的所有方法的数量
            System.out.println(Arrays.stream(cl.getMethods()).count());
        }

        public ObjectOutputStreamTest(OutputStream out) throws IOException {
            super(out);
        }
    }

    private static void deserialization() throws IOException {

        try (FileInputStream fis = new FileInputStream(dir + "/person.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Person xjj = (Person) ois.readObject();
            System.out.println(xjj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void serialization() {
        try (FileOutputStream fos = new FileOutputStream(dir + "/person.ser");
             ObjectOutputStreamTest oos = new ObjectOutputStreamTest(fos)) {
            // DataOutputStream
            // ObjectOutputStream
            // ObjectStreamClass
            // ObjectStreamField

            // 这里Hobby是看一下没写writeObject时进入default分支的代码
            oos.writeObject(new Person("xjj", "12345", new Hobby("hack", "coding")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        serialization();
        deserialization();
    }
}
