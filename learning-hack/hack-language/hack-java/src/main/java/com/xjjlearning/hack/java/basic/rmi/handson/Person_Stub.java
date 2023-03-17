package com.xjjlearning.hack.java.basic.rmi.handson;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * client-endpoint
 * Person_Stub和PersonServer一样，都implements Person
 * 它们都实现了getAge()和getName()两个business method
 * 不同的是PersonServer是真的实现，Person_Stub是建立socket连接，并向Skeleton发请求，然后通过Skeleton调用PersonServer的方法，最后接收返回的结果
 */
public class Person_Stub implements Person {
    private final Socket socket;

    public Person_Stub() throws Throwable {
        // connect to skeleton
        socket = new Socket("127.0.0.1", 9000);
    }

    public int getAge() throws Throwable {
        // pass method name to skeleton
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject("age");
        outStream.flush();
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        return inStream.readInt();
    }

    public String getName() throws Throwable {
        // pass method name to skeleton
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject("name");
        outStream.flush();
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        return (String) inStream.readObject();
    }
}     