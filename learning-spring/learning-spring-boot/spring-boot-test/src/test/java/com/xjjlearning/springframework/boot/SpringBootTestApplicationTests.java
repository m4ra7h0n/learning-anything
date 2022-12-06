package com.xjjlearning.springframework.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// MOCK：根据当前设置确认是否启动web环境，例如使用了Servlet的API就启动web环境，属于适配性的配置
// DEFINED_PORT：使用自定义的端口作为web服务器端口
// RANDOM_PORT：使用随机端口作为web服务器端口( 建议使用，防止端口冲突)
// NONE：不启动web环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
class SpringBootTestApplicationTests {

    @Resource
    private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
        MockHttpServletRequestBuilder builder = get("/");
        ResultActions perform = mockMvc.perform(builder);
        perform.andExpect(status().isOk());
        perform.andExpect(MockMvcResultMatchers.content().string("hello"));

        ResultActions performJson = mockMvc.perform(get("/json"));
        performJson.andExpect(MockMvcResultMatchers.content().json("{\"key\": \"value\"}"));

        perform.andExpect(MockMvcResultMatchers.header().string("Content-Type", "text/plain;charset=UTF-8"));


    }

    @Test
    void collector() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}
