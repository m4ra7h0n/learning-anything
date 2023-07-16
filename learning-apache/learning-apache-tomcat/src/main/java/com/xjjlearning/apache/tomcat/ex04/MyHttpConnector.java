package com.xjjlearning.apache.tomcat.ex04;

import com.xjjlearning.apache.tomcat.ex03.connector.http.HttpProcessor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xjj on 2023/6/17.
 */
public class MyHttpConnector implements Runnable{
    private ServerSocket serverSocket = null;
    private boolean stopped = false;

    @Override
    public void run() {
        while (!stopped) {
            // Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                //                if (debug >= 3)
                //                    log("run: Waiting on serverSocket.accept()");
                socket = serverSocket.accept();
            } catch (Exception e) {

            }

            System.out.println(1);
            MyHttpProcessor processor = new MyHttpProcessor();
            processor.start();

            try {
                Thread.sleep(1); // 下面的assign锁住了Processor, 导致processor中的run方法无法调用await, 无法按照逻辑运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            processor.assign(socket);
            stopped = true;
        }
    }
}
