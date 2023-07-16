package com.xjjlearning.apache.tomcat.ex02;

import javax.servlet.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {
    public void process(Request request, Response response) {
        URLClassLoader loader = null;
        String uri = request.getUri(); // servlet/servletName
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        try {

            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;

            File classPath = new File(Constants.WEB_ROOT);
            // protocol: http, https, file, and jar
            // 这里 urls 是一个 java. net. URL 的对象数组，这些对象指向了加载类时候查找的位置。任何以/结尾的 URL都假设是一个目录。否则，URL 会假定是一个将被下载并在需要的时候打开的JAR文件。
            String file = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();

            // ?
            urls[0] = new URL(null, file, streamHandler);

            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // loadClass
        Class<?> myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}