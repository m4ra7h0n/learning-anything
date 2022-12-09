package com.xjjlearning.springframework.boot.singlton;

public class Singleton {
    public static volatile Singleton singleton; //volatile禁止 初始化对象 和 对象指向内存空间 重排序
    private Singleton() {}
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
