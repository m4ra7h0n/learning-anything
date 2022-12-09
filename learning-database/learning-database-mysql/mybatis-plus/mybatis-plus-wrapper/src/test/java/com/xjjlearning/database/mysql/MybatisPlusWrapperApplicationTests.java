package com.xjjlearning.database.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xjjlearning.database.mysql.mapper.UserMapper;
import com.xjjlearning.database.mysql.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * wrapper用于生成where条件
 */
@SpringBootTest
class MybatisPlusWrapperApplicationTests {
	@Resource
	UserMapper userMapper;

	@Test
	void abstractWrapperTest() {
		/**
		 * 一共四个Wrapper
		 */
		Wrappers.<User>lambdaQuery();
		Wrappers.<User>lambdaUpdate();
		Wrappers.<User>query();
		Wrappers.<User>update();
	}
	@Test
	void testUpdate() {}

	void testSelect() {

		//获取lambda三种方法
		// new QueryWrapper<User>().lambda()
		// Wrappers.<User>lambdaQuery()
		// new LambdaQueryWrapper<User>()

		List<User> userList = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getRoleId, 2L));
		List<User> userList1 = userMapper.selectList(new QueryWrapper<User>().eq("role_id", 2L));
		Assertions.assertEquals(userList1.size(), userList.size());
		print(userList);

		// inSql进行嵌套 in ("select ... where id = ..")
		List<User> userList3 = userMapper.selectList(new QueryWrapper<User>().lambda().
				inSql(User::getRoleId, "select id from role where id = 2;"));
		List<User> userList4 = userMapper.selectList(new LambdaQueryWrapper<User>()
				.inSql(User::getRoleId, "select id from role where id = 2"));
		Assertions.assertEquals(userList3.size(), userList4.size());

		// 嵌套查询
		// select name from user where (role_id = 2 or role_id = 3) and (age > 20)
		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
		//写法1
		lambdaQueryWrapper.and(i -> //整体结构是and连接, i是每个最外层的结构
				i.nested(j -> j.eq(User::getRoleId, 2).or().eq(User::getRoleId, 3)) //j是此外层 where 结构
						.gt(User::getAge, 20));
		//写法2
		lambdaQueryWrapper.nested(i -> i.eq(User::getRoleId, 2L).or().eq(User::getRoleId, 3L))
				.and(i -> i.ge(User::getAge, 20));

		List<User> users = userMapper.selectList(lambdaQueryWrapper);
		users.forEach(user -> System.out.println(user.getName()));

		//注入sql语句
		List<User> users2 = userMapper.selectList(Wrappers.<User>query()
				.apply("role_id = 2"));
		users2.forEach(user -> System.out.println(user.getName()));

//		SELECT id,name,age,email,role_id FROM user * WHERE ( 1 = 1 ) AND ( ( name = ? AND age = ? ) AND ( name = ? OR age = ? ) OR ( name = ? AND age = ? ) )
//																i    and ( j(k and k)  				and j(k or k)  				or j(k and k) )
//		主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.and(i -> i.eq("1" , 1) //最外层
				.nested(j -> j  //次外层
						.and(k -> k.eq("name", "a").eq("age", 2)) //最里层
						.and(k -> k.eq("name", "d").or().eq("age", 4))
						.or(k -> k.eq("name", "b").eq("age", 3))
				 )
		);

//  	SELECT id,name FROM user WHERE (age BETWEEN ? AND ?) ORDER BY role_id ASC,id ASC
		QueryWrapper<User> wrapper1 = new QueryWrapper<>();
		wrapper.between("age", 1, 2).orderByAsc("role_id", "id");
	}

	private <T> void print(List<T> list) {
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(System.out::println);
		}
	}

}
