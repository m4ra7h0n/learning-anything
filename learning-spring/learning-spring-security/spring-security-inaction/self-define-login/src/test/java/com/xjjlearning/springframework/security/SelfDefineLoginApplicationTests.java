package com.xjjlearning.springframework.security;

import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.impl.SysUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SelfDefineLoginApplicationTests {

    @Resource
    SysUserServiceImpl sysUserServiceImpl;

    @Resource
    MockMvc mockMvc;

	@Test
	void contextLoads() {
        SysUser xjj = sysUserServiceImpl.queryByUsername("xjj");
        System.out.println(xjj);
    }

    @Test
    void FooTest() throws Exception {
        mockMvc.perform(get("/foo/test"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "1234")
                .param("login_type", "0")
        ).andExpect(status().isOk());

        mockMvc.perform(post("/process")
                .queryParam("login_type", "0")
                .param("username", "xjj")
                .param("password", "1234")
        ).andExpect(status().isOk());
    }
}
