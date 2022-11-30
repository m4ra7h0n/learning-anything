package com.xjjlearning.springframework.boot.annotation.demo;

import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitColor;
import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitName;
import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitProvider;
import com.xjjlearning.springframework.boot.annotation.demo.iface.RepeatableIface;

import java.lang.reflect.Field;

public class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz) {

        String strFruitName = " 水果名称: ";
        String strFruitColor = " 水果颜色: ";
        String strFruitProvider;

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println(strFruitName);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            } else if (field.isAnnotationPresent(RepeatableIface.class)) {
                RepeatableIface repeatable = field.getAnnotation(RepeatableIface.class);
                for (FruitProvider r : repeatable.value()) {
                    strFruitProvider = " 供应商编号：" + r.id() + " 供应商名称：" + r.name() + " 供应商地址：" + r.address();
                    System.out.println(strFruitProvider);
                }
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider = " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }
}