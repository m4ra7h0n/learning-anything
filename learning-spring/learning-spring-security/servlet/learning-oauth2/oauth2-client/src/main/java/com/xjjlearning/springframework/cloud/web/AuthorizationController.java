package com.xjjlearning.springframework.cloud.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * created by xjj on 2022/12/7
 */
@RestController
public class AuthorizationController {
    private final WebClient webClient;
    private final String messageBaseUri;

    public AuthorizationController(WebClient webClient,
                                   @Value("${messages.base-uri}") String messageBaseUri) {
        this.webClient = webClient;
        this.messageBaseUri = messageBaseUri;
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public String authorizationCodeGrant() {
        return "";
    }
}
