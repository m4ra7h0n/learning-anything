package com.xjjlearning.jvm.classfile;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LocalMemberAnonymousClass {

    static class StaticMemberClass {
        int a = print(getClass());
    }

    public class MemberClass {
        int a = print(getClass());
    }

    public static void main(String[] args) {

        // 匿名类
        new Cloneable() {
            int a = print(getClass());
        };

        // 成员类
        new LocalMemberAnonymousClass().new MemberClass();

        // 静态成员类
        new StaticMemberClass();

        // 局部类
        class LocalClass {
            int a = print(getClass());
        }

        new LocalClass();

        final Object proxyInstance = Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Cloneable.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
        print(proxyInstance.getClass());

        for (Field field : MemberClass.class.getDeclaredFields()) {
            System.out.printf("%s isSynthetic: %s%n", field.getName(), field.isSynthetic());
        }
    }

    private static int print(Class<?> clazz) {
        System.out.printf("%s isMemberClass : %s%n", clazz.getName(), clazz.isMemberClass());
        System.out.printf("%s isLocalClass : %s%n", clazz.getName(), clazz.isLocalClass());
        System.out.printf("%s isAnonymousClass : %s%n", clazz.getName(), clazz.isAnonymousClass());
        System.out.printf("%s isSynthetic: %s%n", clazz.getName(), clazz.isSynthetic());
        System.out.println("-----------------------------");
        return 0; // 返回值没有用，就为了在成员创建的时候可以调用此函数
    }
}
