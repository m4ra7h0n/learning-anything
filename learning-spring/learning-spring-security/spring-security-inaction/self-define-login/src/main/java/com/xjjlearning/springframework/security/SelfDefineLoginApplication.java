package com.xjjlearning.springframework.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelfDefineLoginApplication {

	public static void main(String[] args) {
        // 组装待授权的凭证 然后交给 AuthorizationManager 去认证
        // UsernamePasswordAuthenticationFilter
        // 继承 AbstractAuthenticationProcessingFilter, 包括OAuth2 的认证

		SpringApplication.run(SelfDefineLoginApplication.class, args);
	}

}
