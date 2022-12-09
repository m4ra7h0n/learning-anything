package com.xjjlearning.springframework.boot.annotation.demo;


import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitColor;
import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitName;
import com.xjjlearning.springframework.boot.annotation.demo.iface.FruitProvider;

import static com.xjjlearning.springframework.boot.annotation.demo.iface.FruitColor.Color.RED;

public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = RED)
    private String appleColor;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    @FruitProvider(id = 2, name = "home", address = "home")
    private String appleProvider;

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleName() {
        return appleName;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void displayName() {
        System.out.println("水果的名字是：苹果");
    }
}