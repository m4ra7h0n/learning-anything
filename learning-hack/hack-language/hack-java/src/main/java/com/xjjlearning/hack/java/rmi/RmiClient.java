package com.xjjlearning.hack.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * created by xjj on 2023/1/17
 */
public class RmiClient {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        IRemoteHelloWorld hello = (IRemoteHelloWorld) Naming.lookup("rmi://127.0.0.1:1099/Hello");
        String res = hello.hello("client");
        System.out.println(res);
    }

}
