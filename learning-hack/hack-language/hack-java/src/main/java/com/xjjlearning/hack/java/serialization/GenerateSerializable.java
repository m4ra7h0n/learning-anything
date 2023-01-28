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

            oos.writeObject(new Person("xjj", "12345"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        serialization();
        deserialization();
    }
}
