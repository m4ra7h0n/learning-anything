package com.xjjlearning.springframework.security.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.xjjlearning.springframework.security.entity.RestBody;
import com.xjjlearning.springframework.security.jwt.JwtTokenGenerator;
import com.xjjlearning.springframework.security.jwt.JwtTokenPair;
import com.xjjlearning.springframework.security.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * created by xjj on 2022/12/12
 */
@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler
{
    private JwtTokenGenerator jwtTokenGenerator;

    public CustomLoginSuccessHandler(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("flag", "success_login");
        User principal = (User) authentication.getPrincipal();

        String username = principal.getUsername();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        Set<String> roles = new HashSet<>();
        if (CollectionUtil.isNotEmpty(authorities)) {
            for (GrantedAuthority authority : authorities) {
                String roleName = authority.getAuthority();
                roles.add(roleName);
            }
        }

        JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair(username, roles, null);

        map.put("access_token", jwtTokenPair.getAccessToken());
        map.put("refresh_token", jwtTokenPair.getRefreshToken());

        ResponseUtil.responseJsonWriter(response, RestBody.okData(map, "登录成功"));
    }
}
