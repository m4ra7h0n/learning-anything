package com.xjjlearning.database.mysql;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.xjjlearning.database.mysql.mapper.UserMapper;
import com.xjjlearning.database.mysql.model.User;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisPlusTest
class MybatisPlusApplicationTests {
	@Resource
	UserMapper userMapper;

	@Test
	void contextLoads() {
		User user = new User();
		userMapper.insert(user);
		assertThat(user.getId()).isNotNull();
	}

}
