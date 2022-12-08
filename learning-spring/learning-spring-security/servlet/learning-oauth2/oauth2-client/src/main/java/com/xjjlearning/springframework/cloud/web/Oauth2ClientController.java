package com.xjjlearning.springframework.cloud.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by xjj on 2022/12/8
 */
@RestController
public class Oauth2ClientController {
    // @Resource
    // private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/")
    public String index(@RegisteredOAuth2AuthorizedClient("messaging-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
        // ClientRegistration messageClientRegistration =
        //         this.clientRegistrationRepository.findByRegistrationId("messaging-client");
        // return messageClientRegistration.toString();
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        return accessToken.toString();
    }
}
