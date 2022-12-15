package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.wechet.WechatOAuth2AuthorizationCodeGrantRequestEntityConverter;
import com.xjjlearning.springframework.security.wechet.WechatOAuth2AuthorizationRequestCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by xjj on 2022/12/14
 */
@Configuration
public class SecurityConfiguration {
    private static final String WECHAT_PROVIDER = "wechat";

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {

        OAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver = oAuth2AuthorizationRequestResolver(clientRegistrationRepository);

        OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient = accessTokenResponseClient();

        http
                .authorizeRequests((requests) -> requests.anyRequest().authenticated())
                .oauth2Login()
                // 302跳转到微信并携带 appId + #wechat_redirect 的转换器
                .authorizationEndpoint().authorizationRequestResolver(oAuth2AuthorizationRequestResolver)
                .and()
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient);

        http
                .oauth2Client()
                // 使用code获取token时携带 appId 的转换器
                .authorizationCodeGrant().authorizationRequestResolver(oAuth2AuthorizationRequestResolver);
        return http.build();
    }

    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        tokenResponseClient.setRequestEntityConverter(new WechatOAuth2AuthorizationCodeGrantRequestEntityConverter());

        return null;
    }

    private OAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI);
        resolver.setAuthorizationRequestCustomizer(new WechatOAuth2AuthorizationRequestCustomizer(WECHAT_PROVIDER)::customize);
        return resolver;
    }
}
