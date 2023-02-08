package com.xjjlearning.java.security;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecurityPermission;

public class AccessControllerDemo {
    static {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
    public static void main(String[] args) {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> null);
        SecurityManager sm = System.getSecurityManager();
        sm.checkAccess(new Thread(() -> {}));


        Object context = sm.getSecurityContext();
        SecurityPermission permission = new SecurityPermission("xjj");
        sm.checkPermission(permission, context);
    }
}
