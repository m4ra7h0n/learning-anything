package com.xjjlearning.hack.java.ysoserial.payloads.util;

import java.io.*;
import java.util.Optional;

/**
 * created by xjj on 2023/1/27
 */
public class SerializationUtil {

    public static Optional<Object> deserialize(final byte[] bytes) throws IOException, ClassNotFoundException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return Optional.ofNullable(deserialize(bais));
    }

    public static Optional<Object> deserialize(final Optional<byte[]> op) throws IOException, ClassNotFoundException {
        if (op.isPresent()) {
            return deserialize(op.get());
        }
        return Optional.empty();
    }

    public static Optional<Object> deserialize(final String path) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(path)) {
            return Optional.ofNullable(deserialize(fis));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Object deserialize(final InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        return ois.readObject();
    }

    public static Optional<byte[]> serialize(final Object obj) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        serialize(obj, out);
        return Optional.of(out.toByteArray());
    }

    public static void serialize(final Object obj, final String path) throws IOException {
        final FileOutputStream fos = new FileOutputStream(path);
        serialize(obj, fos);
    }

    public static void serialize(final Object obj, final OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(obj);
    }
}
