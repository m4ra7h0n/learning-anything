package com.xjjlearning.hack.java.rmi.handson;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server-endpoint
 */
public class Person_Skeleton extends Thread {
    private final PersonServer myServer;

    public Person_Skeleton(PersonServer server) {
        // get reference of object server      
        this.myServer = server;
    }

    public void run() {
        try {
            // new socket at port 9000      
            ServerSocket serverSocket = new ServerSocket(9000);

            String method = null; // block
            ObjectInputStream inStream;
            ObjectOutputStream outStream;

            for (;;) {
                // accept stub's request
                Socket socket = serverSocket.accept();

                // each socket
                while (socket != null) {
                    // get stub's request
                    try {
                        inStream = new ObjectInputStream(socket.getInputStream());
                        method = (String) inStream.readObject();
                    } catch (EOFException e) {
                        break;
                    }

                    // check method name
                    if (method.equals("age")) {
                        // execute object server's business method
                        int age = myServer.getAge();
                        outStream = new ObjectOutputStream(socket.getOutputStream());
                        // return result to stub
                        outStream.writeInt(age);
                        outStream.flush();
                    }

                    if (method.equals("name")) {
                        // execute object server's business method
                        String name = myServer.getName();
                        outStream = new ObjectOutputStream(socket.getOutputStream());
                        // return result to stub
                        outStream.writeObject(name);
                        outStream.flush();
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // new object server
        PersonServer person = new PersonServer("Richard", 34);
        Person_Skeleton skel = new Person_Skeleton(person);
        skel.start();
    }
}     