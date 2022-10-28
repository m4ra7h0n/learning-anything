package com.xjjlearning.alibaba.druid;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xjjlearning.alibaba.druid.mapper.UserMapper;
import com.xjjlearning.alibaba.druid.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LearningAlibabaDruidApplicationTests {
	@Resource
	UserMapper userMapper;

	@Test
	void contextLoads() {
		List<User> users = userMapper.selectList(Wrappers.<User>query().orderByAsc("id"));
		users.forEach(user -> System.out.println(user.getName()));
	}

}
