package com.xjjlearning.apache.dubbo.sample.adaptive;

import com.xjjlearning.apache.dubbo.sample.adaptive.fruit.Banana;
import com.xjjlearning.apache.dubbo.sample.adaptive.fruit.iface.Fruit;
import com.xjjlearning.apache.dubbo.sample.adaptive.iface.FruitGranter;
import org.apache.dubbo.common.URL;

public class BananaGranter implements FruitGranter {
    @Override
    public Fruit grant() {
        return new Banana();
    }

    @Override
    public String watering(URL url) {
        System.out.println("watering banana");
        return "watering success";
    }
}
