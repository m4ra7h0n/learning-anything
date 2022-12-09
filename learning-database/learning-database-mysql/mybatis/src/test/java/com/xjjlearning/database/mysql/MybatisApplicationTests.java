package com.xjjlearning.database.mysql;

import com.xjjlearning.database.mysql.model.Country;
import com.xjjlearning.database.mysql.model.SysPrivilege;
import com.xjjlearning.database.mysql.model.SysRole;
import com.xjjlearning.database.mysql.model.SysUser;
import com.xjjlearning.database.mysql.service.impl.CountryServiceImpl;
import com.xjjlearning.database.mysql.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class MybatisApplicationTests {
    @Autowired
    CountryServiceImpl countryService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SqlSession session; //how to rollback ？

    @Test
    public void selectUsersTest(){
        List<SysUser> sysUsers = userService.selectUsers();
        for (SysUser sysUser : sysUsers) {
            System.out.println("sysUser: " + sysUser.getUserName());
            for (SysRole sysRole : sysUser.getRoleList()) {
                System.out.println("--> sysRole: " + sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getPrivilegeList()) {
                    System.out.println("----> sysPrivilege: " + sysPrivilege.getPrivilegeName());
                }
            }
        }
    }

    @Test
    void selectUserByIdTest() {
		SysUser sysUser = userService.selectUser(1001L);
		System.out.println(sysUser.getUserName());
        System.out.println(sysUser.getRole().getRoleName());
    }

    @Test
    void getAllCountryTest() {
		List<Country> allCountry = countryService.getAllCountry();
		allCountry.forEach(country -> System.out.println(country.getCountryName()));
    }

    @Test
    void selectRoleByIdTest() {
		List<SysRole> sysRoles = userService.selectRole(1L);
		sysRoles.forEach(sysRole -> System.out.println(sysRole.getRoleName()));
    }

    @Test
    void insertTest() {
		System.out.println(userService.insertUser("xjj", "123123", null,
				"man", new byte[]{1, 2, 3}, new Date()));
    }
    @Test
    void updateUserTest() {
		SysUser sysUser = new SysUser();
		sysUser.setId(1036L);
		sysUser.setUserName("xxx");
		sysUser.setUserEmail("xx@qq.com");
		userService.updateUser(sysUser);
    }
    @Test
    void deleteUserTest() {
		userService.deleteUser(1L);
    }

    @Test
    void selectRolesByUserIdAndRoleEnabledTest() {
        List<SysRole> sysRoles = userService.selectRole(1L, 1);
        sysRoles.forEach(sysRole -> System.out.println(sysRole.getRoleName()));
    }

    @Test
    void selectUsersByUserAndEmailTest() {
//        if --> not null
		SysUser sysUser = new SysUser();
		sysUser.setUserEmail("2849771625@qq.com");
		List<SysUser> sysUsers = userService.selectUsers(sysUser);
		sysUsers.forEach(sysUser1 -> System.out.println(sysUser1.getUserName()));
    }

    @Test
    void selectUserTest() {
		SysUser sysUser = new SysUser();
//		sysUser.setId(1L);
		sysUser.setUserName("xjj");
		SysUser sysUser1 = userService.selectUser(sysUser);
		System.out.println(sysUser1.getUserEmail());
    }

    @Test
    void selectUserByListTest() {
		List<SysUser> sysUsers = userService.selectUsers(Arrays.asList(1L, 1001L));
		sysUsers.forEach(sysUser -> System.out.println(sysUser.getUserName()));
    }

    @Test
    void insertUserListTest() {
		SysUser sysUser = new SysUser();
		sysUser.setUserEmail("2849771625@qq.com");
		SysUser sysUser2 = new SysUser();
		sysUser2.setUserEmail("xxx@qq.com");
		System.out.println(userService.insertUsers(Arrays.asList(sysUser2, sysUser)));
		System.out.println(sysUser.getId());
    }
    @Test
    void updateUserByMapTest() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("id", 1L);
        stringObjectMap.put("user_name", "adminnn");
        userService.updateUser(stringObjectMap);
    }
}
