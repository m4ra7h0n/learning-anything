package com.xjjlearning.apache.tomcat.ex04;

import java.net.Socket;

/**
 * Created by xjj on 2023/6/17.
 */
public class MyHttpProcessor implements Runnable{
    private boolean available = false;
    private Socket socket = null;
    private boolean stopped = false;

    @Override
    public void run() {
        while (!stopped) {

            // Wait for the next socket to be assigned
            Socket socket = await();
            stopped = true;
        }
    }
    synchronized void assign(Socket socket) {
        // Wait for the Processor to get the previous Socket
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(3);
        // Store the newly available Socket and notify our thread
        this.socket = socket;
        available = true;
        notifyAll(); // notify await ?
    }

    private synchronized Socket await() {
        while (!available) {
            try {
                System.out.println(2);
                wait();
                System.out.println(4);
            } catch (InterruptedException e) {
            }
        }

        Socket socket = this.socket;
        available = false;
        notifyAll();
        return socket;
    }

    public void start() {
        Thread thread = new Thread(this, "");
        thread.setDaemon(true);
        thread.start();
    }
}
