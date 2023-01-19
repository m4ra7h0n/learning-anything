package com.xjjlearning.java.io;

import java.io.*;
import java.util.stream.Stream;

/**
 * created by xjj on 2023/1/18
 */
public class IoStreamTest {
    private static void outputStream() throws IOException {
        try (FileOutputStream out = new FileOutputStream("")) {
            // java7 -> try with resource
            // 对于 AutoCloseable 自动 close
            out.write(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void inputStream() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(12);
        while (out.size() != 10) {
            out.write(System.in.read());
        }
        byte[] bytes = out.toByteArray();
        Stream.of(bytes)
                .flatMap(IoStreamTest::bytesToChars)
                .forEach(System.out::println);

        byte[] input = new byte[10];
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        for (int i = 0; i < input.length; i++) {
            int b = in.read();
            if (b == -1) break;
            input[i] = (byte) b;
        }
        Stream.of(input)
                .flatMap(IoStreamTest::bytesToChars)
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        inputStream();
    }


    /**
     * other utils
     */
    private static Stream<char[]> bytesToChars(byte[] b) {
        char[] chars = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            chars[i] = (char) b[i];
        }
        return Stream.of(chars);
    }
}
