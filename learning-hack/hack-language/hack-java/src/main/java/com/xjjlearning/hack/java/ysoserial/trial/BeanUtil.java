package com.xjjlearning.hack.java.ysoserial.trial;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * created by xjj on 2023/2/9
 */
public class BeanUtil {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object name = PropertyUtils.getProperty(new Cat(), "name");
        System.out.println(name);
    }
}
