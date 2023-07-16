package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.core.StandardContext;

import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by xjj on 2023/7/11.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(1);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map);
//        StandardContext
//        ServletRequestListener
//        HttpServletRequest
//        Object
//        HttpServletResponse
//        PrintWriter
//        Exception
//        RequestFacade
        try {
            String url = "http://123.kpdqe2die2cb57xb28zkf1kp6gc70xom.oastify.com";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
