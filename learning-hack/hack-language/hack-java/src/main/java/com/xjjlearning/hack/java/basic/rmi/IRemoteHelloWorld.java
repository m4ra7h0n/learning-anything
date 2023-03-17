package com.xjjlearning.hack.java.basic.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义要远程调用的函数
 * 必须把被调用类实现的接口，也要放在RMI Registry类加载器能加载的地方
 * 必须要继承 Remote 否则报错
 */
public interface IRemoteHelloWorld extends Remote {
    public String hello(String from) throws RemoteException;
}