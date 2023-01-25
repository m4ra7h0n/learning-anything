package com.xjjlearning.hack.java.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * created by xjj on 2023/1/17
 */
public class RmiServer {

    private static volatile Object lock;

    // 远程调用的类
    public static class RemoteHelloWorld extends UnicastRemoteObject implements IRemoteHelloWorld {
        protected RemoteHelloWorld() throws RemoteException {
            super();
        }

        @Override
        public String hello(String from) throws RemoteException {
            System.out.println("call from " + from);
            return "Hello World!";
        }
    }

    public static void start() throws Exception {
        if (lock == null) {
            synchronized (RmiServer.class) {
                if (lock == null) {
                    lock = new Object();
                    /* 创建registry服务 */
                    LocateRegistry.createRegistry(1099);

                    /*
                     * 两种注册方式, 也可使用 rebind 覆盖
                     * 将我们的类注册到 registry服务
                     * 这种命名的映射是使用 JNDI 接口的
                     */
                    Naming.bind("rmi://127.0.0.1:1099/Hello", new RemoteHelloWorld());
                    // Naming.bind("rmi://127.0.0.1:1099/Hello", new PocWithRuntime());

                    // LocateRegistry.getRegistry("127.0.0.1", 1099)
                    //         .bind("hello", new RemoteHelloWorld());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
