package com.xjjlearning.springframework.boot.supplier;

import com.xjjlearning.springframework.boot.supplier.pojo.Person;
import com.xjjlearning.springframework.boot.supplier.pojo.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class SupplierDemoApplication {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 构建 BeanDefinition
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(User.class);

        // 构造器引用  这里调用的是无参构造器
        rootBeanDefinition.setInstanceSupplier(Person::new);

        // 注册BeanDefinition
        factory.registerBeanDefinition("user", rootBeanDefinition);
        Object user = factory.getBean("user");  //最终跟踪到 obtainFromSupplier
        // 返回的是 Person 对象
        System.out.println("结果：" +user);
    }
}
