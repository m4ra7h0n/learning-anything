package com.xjjlearning.hack.java.ysoserial.payloads;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * created by xjj on 2023/1/26
 */
public class DeURLDNS {

    private final String path = System.getProperty("user.dir")
            + "/learning-hack/hack-language/hack-java/src/main/resources/ysoserial";

    private void deserialization() {

        try (FileInputStream fis = new FileInputStream(path + "/urldns.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // 在HashMap中的readObject中
            // putVal(hash(key), key, value, false, false); 处下断点
            ois.readObject();

        } catch (Exception ignore) {}

    }

    public static void main(String[] args) {
        new DeURLDNS().deserialization();
    }
}
