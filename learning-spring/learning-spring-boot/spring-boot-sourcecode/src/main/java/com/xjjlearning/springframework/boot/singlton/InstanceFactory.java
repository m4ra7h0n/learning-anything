package com.xjjlearning.springframework.boot.singlton;

public class InstanceFactory {
    private static class InstanceHolder {
        // class初始化的时候有 LC锁
        // 当某个线程开始初始化类的时候先抢锁 设置state 为 initializing, 释放锁 其他线程获取锁后在 condition 等待
        public static Instance instance = new Instance();
        //避免了 分配空间 和 引用赋值的重排序
    }
    public static Instance getInstance() {
        return InstanceHolder.instance; // 调用初始化 InstanceHolder
    }
}
class Instance {}