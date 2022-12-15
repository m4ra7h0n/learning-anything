package com.xjjlearning.springframework.security.wechet;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * created by xjj on 2022/12/14
 */
public class WechatOAuth2AuthorizationRequestCustomizer {
    private static final String WECHAT_APP_ID = "appid";
    private static final String WECHAT_FRAGMENT = "wechat_redirect";
    private final String wechatRegistrationId;

    public WechatOAuth2AuthorizationRequestCustomizer(String wechatRegistrationId) {
        Assert.notNull(wechatRegistrationId, "WeChat registrationId flag must not be null !");
        this.wechatRegistrationId = wechatRegistrationId;
    }

    public void customize(OAuth2AuthorizationRequest.Builder builder) {
        builder.attributes((attributes) -> {
            String registrationId = (String) attributes.get(OAuth2ParameterNames.REGISTRATION_ID);
            if (wechatRegistrationId.equals(registrationId)) {
                builder.parameters(this::wechetParameterConsumer);
                builder.authorizationRequestUri(this::authorizationRequestUriFunction);
            }
        });
    }

    private void wechetParameterConsumer(Map<String, Object> parameters) {
        LinkedHashMap<String, Object> linkedParameters = new LinkedHashMap<>();
        parameters.forEach((k, v) -> {
                if (OAuth2ParameterNames.CLIENT_ID.equals(k)) {
                    linkedParameters.put(WECHAT_APP_ID, v);
                }else {
                    linkedParameters.put(k, v);
                }
        });
        parameters.clear();
        parameters.putAll(linkedParameters);
    }

    private URI authorizationRequestUriFunction(UriBuilder builder) {
        return builder.fragment(WECHAT_FRAGMENT).build();
    }
}
