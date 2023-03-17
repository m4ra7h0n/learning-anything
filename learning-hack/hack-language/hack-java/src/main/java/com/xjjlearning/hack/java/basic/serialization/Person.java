package com.xjjlearning.hack.java.basic.serialization;

import java.io.*;
import java.util.Base64;

/**
 * created by xjj on 2023/1/26
 */
public class Person implements Serializable {


    public String name;
    public String password;
    public Hobby hobby;
    transient String string = "hello";

    public Person(String name, String password, Hobby hobby) {
        this.name = name;
        this.password = password;
        this.hobby = hobby;
    }


    private void writeObject(ObjectOutputStream oos) throws IOException {
        // 这个方法是干嘛的？
        // oos.defaultWriteObject();
        // 主要是调用 defaultWriteFields(), 序列化原生类型以及其他字段

        // 流中写入一个String
        oos.writeObject("this is a message");

        // 获取对象中的字段
        ObjectOutputStream.PutField putFields = oos.putFields();
        // 加密
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        // 手动输入序列化数据
        putFields.put("password", encryptedPassword);
        putFields.put("name", name);
        putFields.put("hobby", hobby);
        oos.writeFields();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // ois.defaultReadObject();

        // 读取写入String信息
        String message = (String) ois.readObject();
        System.out.println(message);

        // 获取原始流中的密码
        ObjectInputStream.GetField getFields = ois.readFields();
        // 解密
        String encryptedPassword = (String) getFields.get("password", "");
        byte[] passwordBytes = Base64.getDecoder().decode(encryptedPassword);
        // 手动赋值序列化数据
        this.password = new String(passwordBytes);
        this.name = (String) getFields.get("name", "");
        this.hobby = (Hobby) getFields.get("hobby", null);
    }

    @Override
    public String toString() {
        return "name: " + name + ", password: " + password;
    }


    /**
     * writeReplace 存在时自定义的writeObject失效
     */
    // private Object writeReplace() throws ObjectStreamException {
    //     List<Object> list = new ArrayList<>();
    //     list.add(name);
    //     list.add(password);
    //     return list;
    // }

    /**
     * readResolve 会在 readObject 调用之后自动调用，它最主要的目的就是对反序列化的对象进行修改后返回。
     */
    // private Object readResolve() throws ObjectStreamException {
    //     return password == null ?
    //             new Person("xjj", "1234") :
    //             new Person("xxx", "1111");
    // }
}
