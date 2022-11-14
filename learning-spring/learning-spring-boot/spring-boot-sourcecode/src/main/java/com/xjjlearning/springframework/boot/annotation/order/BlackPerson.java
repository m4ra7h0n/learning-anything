package com.xjjlearning.springframework.boot.annotation.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.OrderComparator;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1) //第二个执行
public class BlackPerson implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        OrderComparator orderComparator = new OrderComparator();
        System.out.println("----BlackPersion----");
    }
}
