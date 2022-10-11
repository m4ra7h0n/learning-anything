package com.xjjlearning.apache.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

import java.util.ServiceLoader;

@SpringBootConfiguration
public class SpiMain implements CommandLineRunner {

    private static void javaSpi() {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        serviceLoader.forEach(driver -> System.out.println(driver.connect()));
    }

    /**
     * getExtension笔记:
     * getExtension主要做缓存, 主要代码在createExtension
     * createExtension()
     * ->getExtensionClasses():做缓存, 得到我们的扩展类
     *   ->loadExtensionClasses()->loadDirectory():加载目录中的类('internal/', 'dubbo/', 'services/'), 反射并缓存
     * ->injectExtension():给我们扩展类中setter参数特殊类注入, 此类必须包含@SPI注解
     * ->cachedWrapperClasses:包装类集合, 将我们的扩展类进行包装, 然后注入
     *
     */
    private static void dubboSpi() {
        ExtensionLoader<Driver> driverExtensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        MysqlDriver mysqlDriver = (MysqlDriver) driverExtensionLoader.getExtension("MysqlDriver");
        System.out.println(mysqlDriver.connect());
    }

    @Override
    public void run(String... args) throws Exception {
        javaSpi();
        dubboSpi();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpiMain.class);
    }
}
