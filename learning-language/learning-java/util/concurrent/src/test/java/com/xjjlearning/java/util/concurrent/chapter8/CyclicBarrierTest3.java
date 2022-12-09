package com.xjjlearning.java.util.concurrent.chapter8;import java.util.concurrent.BrokenBarrierException;import java.util.concurrent.CyclicBarrier;public class CyclicBarrierTest3 {    static CyclicBarrier c = new CyclicBarrier(3);    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {        for (int i = 0; i < 2; i++) {            Thread thread = new Thread(() -> {                try {                    c.await();                } catch (Exception ignored) {                    System.out.println("error");                }            });            thread.start();//            thread.interrupt();        }        System.out.println(c.getNumberWaiting());        System.out.println(c.isBroken());        try {            c.await(); //throw exception when any blocked thread interrupted            System.out.println(1111);        } catch (Exception e) {            System.out.println(c.isBroken());        }    }}