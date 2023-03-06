package com.xjjlearning.jvm.deeptoeasy.test;

/**
 * Created by xjj on 2023/3/6.
 */
public class LinkAndVerifyTest {
    /**
     * 通过修改hotspot源码
     * 验证链接阶段在类初始化之前
     */
    public static void main(String[] args) throws Exception {
        Class<?> clazz = LinkAndVerifyTest.class.getClassLoader()
                .loadClass("com.xjjlearning.jvm.deeptoeasy.test.VerifyTest");
        System.out.println(clazz);
        try {
            Object obj= clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
