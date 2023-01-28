package com.xjjlearning.hack.java.ysoserial.trial;

import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;

import java.io.*;
import java.util.Optional;

/**
 * created by xjj on 2023/1/28
 */
// https://blog.csdn.net/securitit/article/details/106694445
public class Externalization implements Externalizable {
    private int i;
    private String s;//没有初始化

    public Externalization() {
        //默认构造函数必须有，而且必须是public
        System.out.println("Externalization默认构造函数");
    }

    public Externalization(String s, int i) {
        //s,i只是在带参数的构造函数中进行初始化。
        System.out.println("Externalization带参数构造函数");
        this.s = s;
        this.i = i;
    }

    public String toString() {
        return s + i;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        System.out.println("调用readExternal（）方法");
        // 自定义反序列化的时候数据如何初始化
        s = in.readObject() + "is, is, is: " ;//在反序列化时，需要初始化s和i，否则只是调用默认构造函数，得不到s和i的值
        i = in.readInt() + 999;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("调用writeExternal（）方法");
        out.writeObject(s); //如果不将s和i的值写入的话，那么在反序列化的时候，就不会得到这些值。
        out.writeInt(i);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("序列化之前");
        Externalization b = new Externalization("This String is " , 47);
        System.out.println(b);

        System.out.println("序列化操作，writeObject");
        Optional<byte[]> serialize = SerializationUtil.serialize(b);
        System.out.println("反序列化之后,readObject");
        // 反序列化调用默认构造函数
        Optional<Object> deserialize = SerializationUtil.deserialize(serialize);

        System.out.println(deserialize.get());
    }
}
