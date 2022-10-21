package com.xjjlearning.apache.dubbo.sample.adaptive;

import com.xjjlearning.apache.dubbo.sample.adaptive.iface.FruitGranter;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

//案例详解见如下网址
//https://blog.csdn.net/wang0907/article/details/122516595
@SpringBootConfiguration
public class AdaptiveMain implements CommandLineRunner {

    /**
     * getAdaptiveExtension(): 缓存函数
     *   ->createAdaptiveExtension()->getAdaptiveExtensionClass()
     *     ->getExtensionClasses():上面分析过
     *     ->createAdaptiveExtensionClass()
     *       ->AdaptiveClassCodeGenerator().generate():字节码生成代码(动态拼接)
     *       ->compile():编译
     */
    @Override
    public void run(String... args) throws Exception {
        // 首先创建一个模拟用的URL对象
        URL url = URL.valueOf("dubbo://127.0.0.1:20880");  //根据SPI注解, 默认使用apple
        URL url2 = URL.valueOf("dubbo://127.0.0.1:20880?fruit.granter=banana"); //fruit.granter是FruitGranter接口简单名称的点分形式
        URL url3 = URL.valueOf("dubbo://127.0.0.1:20880?find.fruit.extension=banana");  //自定义adaptive的value
        // 通过ExtensionLoader获取一个FruitGranter对象
        FruitGranter granter = ExtensionLoader.getExtensionLoader(FruitGranter.class).getAdaptiveExtension();
        // 使用该FruitGranter调用其"自适应标注的"方法，获取调用结果
        String result = granter.watering(url);
        System.out.println(result);

        //会发现Protocol的SPI注解中使用的是dubbo, 则动态扩展使用的是dubbo协议
        Protocol adaptiveExtension = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    }

    public static void main(String[] args) {
        SpringApplication.run(AdaptiveMain.class);
    }
}
