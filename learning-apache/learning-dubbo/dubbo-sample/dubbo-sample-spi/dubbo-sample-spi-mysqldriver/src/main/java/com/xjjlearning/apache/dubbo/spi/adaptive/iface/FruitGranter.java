package com.xjjlearning.apache.dubbo.spi.adaptive.iface;

import com.xjjlearning.apache.dubbo.spi.adaptive.fruit.iface.Fruit;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("apple")
public interface FruitGranter {

    Fruit grant();

    //Adaptive 可注解在类或方法上。当 Adaptive 注解在类上时，Dubbo 不会为该类生成代理类。注解在方法（接口方法）上时，Dubbo 则会为该方法生成代理逻辑
    @Adaptive(value = "find.fruit.extension")
    String watering(URL url);
}
