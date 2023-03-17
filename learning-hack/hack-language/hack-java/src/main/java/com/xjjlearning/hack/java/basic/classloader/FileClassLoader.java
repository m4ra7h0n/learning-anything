package com.xjjlearning.hack.java.basic.classloader;

import java.io.*;

// 传入 .class 文件的绝对路径，加载 Class
public class FileClassLoader extends ClassLoader {

    // 重写了 findClass 方法
    @Override
    public Class<?> findClass(String path) throws ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);
        if (classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(classBytes, 0, classBytes.length);
    }

    private byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new
             ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }
}