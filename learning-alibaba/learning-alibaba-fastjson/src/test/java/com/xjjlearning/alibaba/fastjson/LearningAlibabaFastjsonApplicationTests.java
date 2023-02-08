package com.xjjlearning.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LearningAlibabaFastjsonApplicationTests {

	@Test
	void contextLoads() {
	}

    private final List<Person> listOfPersons = new ArrayList<>();

    @Before
    public void setUp() {
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));
    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput= JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
    }

}
