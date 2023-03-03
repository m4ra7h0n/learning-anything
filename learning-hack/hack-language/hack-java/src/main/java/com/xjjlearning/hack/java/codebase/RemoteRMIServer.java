package com.xjjlearning.hack.java.codebase;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * created by xjj on 2023/1/25
 */
// 远程地址
public class RemoteRMIServer {

    public static class Calc extends UnicastRemoteObject implements ICalc {
        public Calc() throws RemoteException {
        }

        @Override
        public Integer sum(List<Integer> params) throws RemoteException {
            return params.stream()
                    .reduce(Integer::sum)
                    .get();
        }

    }
    private static void start() throws Exception {
        // 被攻击的条件之一, 需要SecurityManager, 在LoaderHandler.class中
        // 条件2: java.rmi.server.useCodebaseOnly = false
        if (System.getSecurityManager() == null) {
            System.out.println("Setting up SecurityManager");
            System.setSecurityManager(new SecurityManager());
        }
        Calc h = new Calc();

        LocateRegistry.createRegistry(1099);
        System.out.println("create registry ok");

        // 或者使用ReferenceWrapper
        Naming.bind("refObj", h);
        System.out.println("binding ok");
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
