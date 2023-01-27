package com.xjjlearning.hack.java.ysoserial.trial;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * created by xjj on 2023/1/27
 */
public class JavaProxy {
    static class ExampleInvocationHandler implements InvocationHandler {
        protected Map map;

        public ExampleInvocationHandler(Map map) {
            this.map = map;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("get")) {
                return "Hacked Object";
            }
            return method.invoke(this.map, args);
        }
    }

    public static void main(String[] args) {
        // loader: 用哪个类加载器去加载代理对象
        // interfaces:动态代理类需要实现的接口
        // h:动态代理方法在执行时，会调用h里面的invoke方法去执行
        Map map = (Map) Proxy.newProxyInstance(
                Map.class.getClassLoader(),
                new Class[]{Map.class},
                new ExampleInvocationHandler(new HashMap()));
        map.put("hello", "world");
        System.out.println(map.get("hello"));
    }
}
