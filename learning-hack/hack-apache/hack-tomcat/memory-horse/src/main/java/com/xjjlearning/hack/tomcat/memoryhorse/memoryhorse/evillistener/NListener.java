package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse.evillistener;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * Created by xjj on 2023/7/13.
 */
public class NListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println(1234);
    }
}
