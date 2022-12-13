package com.xjjlearning.springframework.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    /**
     * 不加自定义异常的结果, 但是增加自定义登录结果处理器(jwt), 增加登录处理url
     * @throws Exception
     */
    @Test
    void FooTest() throws Exception {
        mockMvc.perform(get("/foo/test"))
                .andExpect(status().is3xxRedirection());

        // 401
        MvcResult mvcResult = mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "1234") // 密码不对
                .param("login_type", "0")
        ).andExpect(status().isOk()).andReturn(); // 因为自定义登录结果
        System.out.println(mvcResult.getResponse().getContentAsString());

        // 200
        MvcResult mvcResult1 = mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "12345")
                .param("login_type", "0")
        ).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult1.getResponse().getContentAsString());
    }

    /**
     * 增加自定义异常, 增加自定义登录结果处理(jwt), 增加登录处理url
     * @throws Exception
     */
    @Test
    void exceptionHandlingFooTest() throws Exception {
        // 401
        MvcResult mvcResult = mockMvc.perform(get("/foo/test"))
                .andExpect(status().isUnauthorized()).andReturn(); //因为系统自带的未授权结果是301跳转 但是我们自定义之后就返回来未授权401
        System.out.println(mvcResult.getResponse().getContentAsString());

        // 401
        MvcResult mvcResult2 = mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "1234") // 密码不对
                .param("login_type", "0")
        ).andExpect(status().isOk()).andReturn(); // 因为自定义登录结果
        System.out.println(mvcResult2.getResponse().getContentAsString());

        // 200
        MvcResult mvcResult1 = mockMvc.perform(post("/process")
                .param("username", "xjj")
                .param("password", "12345")
                .param("login_type", "0")
        ).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult1.getResponse().getContentAsString());
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

    @Test
    void authorizeTest() throws Exception {
        MvcResult mvcResult1 = mockMvc.perform(post("/process")
                        .param("username", "xjj")
                        .param("password", "12345")
                        .param("login_type", "0")).andReturn();
        String contentAsString = mvcResult1.getResponse().getContentAsString();
        System.out.println(contentAsString);
        JSONObject jsonObject = JSONUtil.parseObj(contentAsString).getJSONObject("data");
        Object access_token = jsonObject.get("access_token");

        mockMvc.perform(get("/foo/test2").header("Authorization", "Bearer " + access_token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/foo/bar").header("Authorization", "Bearer " + access_token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/foo/param/xjj").header("Authorization", "Bearer " + access_token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/foo/postfilter").header("Authorization", "Bearer " + access_token))
                .andExpect(status().isOk());
    }
}
