package com.xjjlearning.java.security;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class AccessControllerDemo {
    public static void main(String[] args) {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> null);
    }
}
