package com.xjjlearning.hack.java.codebase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * created by xjj on 2023/1/25
 */
public interface ICalc extends Remote {
    Integer sum(List<Integer> params) throws RemoteException;
}
