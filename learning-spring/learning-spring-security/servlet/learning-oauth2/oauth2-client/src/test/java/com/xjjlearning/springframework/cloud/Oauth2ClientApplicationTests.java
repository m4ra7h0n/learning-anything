package com.xjjlearning.springframework.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootTest
class Oauth2ClientApplicationTests {
    // https://www.baeldung.com/spring-5-webclient

    @Test
    void contextLoads() {
        WebClient webClient = WebClient.create();
        WebClient webClient2 = WebClient.create("http://www.baidu.com");
        WebClient webClient3 = WebClient.builder()
                .baseUrl("http://www.baidu.com")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
    }

}
