package com.xjjlearning.apache.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

import java.util.ServiceLoader;

@SpringBootConfiguration
public class SpiMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpiMain.class);
    }

    private static void javaSpi() {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        serviceLoader.forEach(driver -> System.out.println(driver.connect()));
    }

    /**
     * ExtensionLoader.getExtensionLoader()
     * ConcurrentHashMap<Class<?>, ExtensionLoader<?>> 缓存
     * 主要代码在createExtension()
     */
    private static void dubboSpi() {
        ExtensionLoader<Driver> driverExtensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver mysqlDriver = driverExtensionLoader.getExtension("MysqlDriver");
        System.out.println(mysqlDriver.connect());
    }

    @Override
    public void run(String... args) throws Exception {
        javaSpi();
        dubboSpi();
    }
}
