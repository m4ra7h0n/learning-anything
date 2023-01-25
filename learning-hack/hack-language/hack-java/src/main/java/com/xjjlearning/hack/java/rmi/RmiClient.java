package com.xjjlearning.hack.java.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * created by xjj on 2023/1/17
 */
public class RmiClient {

    public static void main(String[] args) throws Exception {
        IRemoteHelloWorld hello = (IRemoteHelloWorld) Naming.lookup("rmi://127.0.0.1:1099/Hello");
        String res = hello.hello("client");
        System.out.println(res);

        try {
            // Naming.rebind("rmi://127.0.0.1:1099/Hello", new RmiServer.RemoteHelloWorld());
            Naming.rebind("rmi://192.168.31.141:1099/Hello", new RmiServer.RemoteHelloWorld());
        } catch (RemoteException e) {
            System.out.println("we can't rebind without localhost");
        }

        // we can list the one who bound the registry anywhere
        String[] bound = Naming.list("rmi://192.168.31.141:1099");
        Arrays.stream(bound).forEach(System.out::println);

    }

}
