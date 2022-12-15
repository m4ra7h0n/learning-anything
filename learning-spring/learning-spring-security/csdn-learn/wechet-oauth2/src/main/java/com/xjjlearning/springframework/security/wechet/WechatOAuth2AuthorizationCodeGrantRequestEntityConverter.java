package com.xjjlearning.springframework.security.wechet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * created by xjj on 2022/12/14
 */
public class WechatOAuth2AuthorizationCodeGrantRequestEntityConverter
        implements Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {
    private static final HttpHeaders DEFAULT_TOKEN_REQUEST_HEADERS = getDefaultTokenRequestHeaders();
    private static final String WECHAT_ID = "wechat";
    private final Converter<OAuth2AuthorizationCodeGrantRequest,RequestEntity<?>> defaultConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();
    /**
     * Returns the {@link RequestEntity} used for the Access Token Request.
     *
     * @param authorizationCodeGrantRequest the authorization code grant request
     * @return the {@link RequestEntity} used for the Access Token Request
     */
    @Override
    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();
        HttpHeaders headers = getTokenRequestHeaders(clientRegistration);

        String tokenUri = clientRegistration.getProviderDetails().getTokenUri();
        // 针对微信的定制  WECHAT_ID表示为微信公众号专用的registrationId
        if (WECHAT_ID.equals(clientRegistration.getRegistrationId())) {
            MultiValueMap<String, String> queryParameters = this.buildWechatQueryParameters(authorizationCodeGrantRequest);
            URI uri = UriComponentsBuilder.fromUriString(tokenUri).queryParams(queryParameters).build().toUri();
            return RequestEntity.get(uri).headers(headers).build();
        }else {
            return defaultConverter.convert(authorizationCodeGrantRequest);
        }
    }


    private MultiValueMap<String, String> buildWechatQueryParameters(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        // 获取微信的客户端配置
        ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();
        OAuth2AuthorizationExchange authorizationExchange = authorizationCodeGrantRequest.getAuthorizationExchange();
        MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
        // grant_type
        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, authorizationCodeGrantRequest.getGrantType().getValue());
        // code
        formParameters.add(OAuth2ParameterNames.CODE, authorizationExchange.getAuthorizationResponse().getCode());
        // 如果有redirect-uri
        String redirectUri = authorizationExchange.getAuthorizationRequest().getRedirectUri();
        if (redirectUri != null) {
            formParameters.add(OAuth2ParameterNames.REDIRECT_URI, redirectUri);
        }
        //appid
        formParameters.add("appid", clientRegistration.getClientId());
        //secret
        formParameters.add("secret", clientRegistration.getClientSecret());
        return formParameters;
    }


    static HttpHeaders getTokenRequestHeaders(ClientRegistration clientRegistration) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(DEFAULT_TOKEN_REQUEST_HEADERS);
        if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.equals(clientRegistration.getClientAuthenticationMethod())
                || ClientAuthenticationMethod.BASIC.equals(clientRegistration.getClientAuthenticationMethod())) {
            String clientId = encodeClientCredential(clientRegistration.getClientId());
            String clientSecret = encodeClientCredential(clientRegistration.getClientSecret());
            headers.setBasicAuth(clientId, clientSecret);
        }
        return headers;
    }

    private static String encodeClientCredential(String clientCredential) {
        try {
            return URLEncoder.encode(clientCredential, StandardCharsets.UTF_8.toString());
        }
        catch (UnsupportedEncodingException ex) {
            // Will not happen since UTF-8 is a standard charset
            throw new IllegalArgumentException(ex);
        }
    }

    private static HttpHeaders getDefaultTokenRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        final MediaType contentType = MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        headers.setContentType(contentType);
        return headers;
    }
}
