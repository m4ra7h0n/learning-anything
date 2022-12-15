package com.xjjearning.springframework.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GiteeLoginApplication {

	public static void main(String[] args) {

        /**
         * the flow of this program
         */

        // 1.visit /foo/hello directly -> anonymous ->
        // ExceptionTranslationFilter.handleAccessDeniedException()
        // redirect to localhost:8082/login to choose the authorization server to login, choose gitee
        // 2.localhost get http://localhost:8082/oauth2/authorization/gitee  -- provider


        // OAuth2AuthorizationRequestRedirectFilter -- authorization-uri  // override class OAuth2AuthorizationRequestResolver to implement the resolve() method
        // 3.localhost Redirecting to https://gitee.com/oauth/authorize?response_type=code&client_id=a280a18982dac0bef77e5833b0bf9e2aa6c11ed98e63a6c9200abbca674c7724&scope=user_info&state=1vVhtaY4-H5AyBOKxbAN4sv8liYzLpGvPMcmN2G4nag%3D&redirect_uri=http://localhost:8082/login/oauth2/code/gitee

        // 4.click to authorize
        // 5.gitee Redirecting to /login/oauth2/code/gitee?code=ba672823a29380a88cbda65b051ef7f78d84ce5c578963eb2626589c7efbe5e0&state=1vVhtaY4-H5AyBOKxbAN4sv8liYzLpGvPMcmN2G4nag%3D':

        // OAuth2LoginAuthenticationFilter
        // OAuth2LoginAuthenticationProvider
        // OAuth2LoginAuthenticationProvider
        // 6.post https://gitee.com/oauth/token  -- token-uri
        // 7.get  https://gitee.com/api/v5/user -- user-info-uri

        // 8.localhost Redirecting to http://localhost:8082/foo/hello


        /**
            properties info classes
        */

        // ClientRegistration           //can use the properties defined in application.yml
        // OAuth2ClientPropertiesRegistrationAdapter
        // CommonOAuth2Provider

		SpringApplication.run(GiteeLoginApplication.class, args);
	}
    
    @RestController
    static class CallBack {
        @GetMapping("/foo/hello")
        public Map<String, Object> foo(@RegisteredOAuth2AuthorizedClient
                                       OAuth2AuthorizedClient client) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            HashMap<String, Object> stringHashMap = new HashMap<>();
            stringHashMap.put("giteeOAuth2Client", client.toString());
            stringHashMap.put("authentication", authentication.toString());
            return  stringHashMap;
        }
        @GetMapping("/")
        public Map<String, String> index() {
            return Collections.singletonMap("msg", "oauth2Login success!");
        }
    }

}
