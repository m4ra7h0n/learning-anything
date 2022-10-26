package com.xjjlearning.database.mysql;

import com.xjjlearning.database.mysql.mapper.UserMapper;
import com.xjjlearning.database.mysql.model.Country;
import com.xjjlearning.database.mysql.model.SysPrivilege;
import com.xjjlearning.database.mysql.model.SysRole;
import com.xjjlearning.database.mysql.model.SysUser;
import com.xjjlearning.database.mysql.service.impl.CountryServiceImpl;
import com.xjjlearning.database.mysql.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MybatisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

	@Autowired
	CountryServiceImpl countryService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	SqlSession session; //how to rollback ？

	@Override
	public void run(String... args) throws Exception {
//		List<Country> allCountry = countryService.getAllCountry();
//		allCountry.forEach(country -> System.out.println(country.getCountryName()));
//
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
//
//		SysUser sysUser = userService.selectUser(1L);
//		System.out.println(sysUser.getUserName());
//		sysUser.getRoleList().forEach(sysRole -> System.out.println(sysRole.getRoleName()));
//
//		List<SysRole> sysRoles = userService.selectRoles(1L);
//		sysRoles.forEach(sysRole -> System.out.println(sysRole.getRoleName()));
//
//		System.out.println(userService.insertUser("xjj", "123123", null,
//				"man", new byte[]{1, 2, 3}, new Date()));
//
//		SysUser sysUser = new SysUser();
//		sysUser.setId(1036L);
//		sysUser.setUserName("xxx");
//		sysUser.setUserEmail("xx@qq.com");
//		userService.updateUser(sysUser);

//		userService.deleteUser(1L);

//		List<SysRole> sysRoles = userService.selectRole(1L, 1);
//		sysRoles.forEach(sysRole -> System.out.println(sysRole.getRoleName()));

		//if --> not null
//		SysUser sysUser = new SysUser();
//		sysUser.setUserEmail("2849771625@qq.com");
//		List<SysUser> sysUsers = userService.selectUsers(sysUser);
//		sysUsers.forEach(sysUser1 -> System.out.println(sysUser1.getUserName()));

//		SysUser sysUser = new SysUser();
////		sysUser.setId(1L);
//		sysUser.setUserName("xjj");
//		SysUser sysUser1 = userService.selectUser(sysUser);
//		System.out.println(sysUser1.getUserEmail());

//		List<SysUser> sysUsers = userService.selectUsers(Arrays.asList(1L, 1001L));
//		sysUsers.forEach(sysUser -> System.out.println(sysUser.getUserName()));
//
//		SysUser sysUser = new SysUser();
//		sysUser.setUserEmail("2849771625@qq.com");
//		SysUser sysUser2 = new SysUser();
//		sysUser2.setUserEmail("xxx@qq.com");
//		System.out.println(userService.insertUsers(Arrays.asList(sysUser2, sysUser)));
//		System.out.println(sysUser.getId());

//		Map<String, Object> stringObjectMap = new HashMap<>();
//		stringObjectMap.put("id", 1L);
//		stringObjectMap.put("user_name", "adminnn");
//		userService.updateUser(stringObjectMap);
	}
}
