package com.xjjlearning.springframework.security;

import com.xjjlearning.springframework.security.builders.CopyFilterOrderRegistration;
import com.xjjlearning.springframework.security.entity.Role;
import com.xjjlearning.springframework.security.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class SpringSecurityInaction2022ApplicationTests {
    @Resource
    UserRoleService userRoleService;

    @Test
    void contextLoads() {
        List<Role> xjj = userRoleService.findRolesByUsername("xjj");
        xjj.forEach(System.out::println);
    }

    @Test
    void testFilterToOrder() {
        CopyFilterOrderRegistration copyFilterOrderRegistration = new CopyFilterOrderRegistration();
        Map<String, Integer> filterToOrder = copyFilterOrderRegistration.getFilterToOrder();
        TreeMap<Integer, String> map = new TreeMap<>();
        filterToOrder.forEach((name, order) -> map.put(order, name));
        map.forEach((order, name) -> System.out.println("顺序: " + order + "类名: " + name));
    }

}
