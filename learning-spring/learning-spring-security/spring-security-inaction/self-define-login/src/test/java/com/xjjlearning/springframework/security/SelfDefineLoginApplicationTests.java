package com.xjjlearning.springframework.security;

import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.jwt.JwtTokenGenerator;
import com.xjjlearning.springframework.security.jwt.JwtTokenPair;
import com.xjjlearning.springframework.security.service.impl.SysUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;

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

        MvcResult mvcResult1 = mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "12345")
                .param("login_type", "0")
        ).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult1.getResponse().getContentAsString());

        // MvcResult mvcResult = mockMvc.perform(post("/process")
        //         .queryParam("login_type", "0")
        //         .param("username", "xjj")
        //         .param("password", "1234")
        // ).andExpect(status().isOk()).andReturn();
        // System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(value = "xjj", password = "12345")
    public void withMockUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foo/test"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Test
    public void jwtTest() {
        HashSet<String> roles = new HashSet<>();
        HashMap<String, String> additional = new HashMap<>();
        additional.put("uname", "xjj");

        JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair("133", roles, additional);

        System.out.println("jwtTokenPair = " + jwtTokenPair);
    }
}
