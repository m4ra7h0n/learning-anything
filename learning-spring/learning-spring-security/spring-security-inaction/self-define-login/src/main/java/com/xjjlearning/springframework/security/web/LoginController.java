package com.xjjlearning.springframework.security.web;

import com.xjjlearning.springframework.security.entity.Rest;
import com.xjjlearning.springframework.security.entity.RestBody;
import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.SysUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by xjj on 2022/12/11
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    SysUserService sysUserService;

    @PostMapping("/failure")  // notice post
    public Rest logout() {
        return RestBody.failure(HttpStatus.UNAUTHORIZED.value(), "登录失败");
    }
    @PostMapping("/success")
    public Rest success() {
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        // 数据库查询
        SysUser xjj = sysUserService.queryByUsername("xjj");
        xjj.setEncodePassword("[PROTECT]");
        return RestBody.okData(xjj, "成功");
    }
}
