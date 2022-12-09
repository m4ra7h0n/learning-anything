package com.xjjlearning.springframework.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * created by xjj on 2022/12/8
 */
@Configuration
public class OAuth2ClientWebConfig {
    // /**
    //  * see: https://docs.spring.io/spring-security/reference/servlet/oauth2/client/authorized-clients.html
    //  * The ServletOAuth2AuthorizedClientExchangeFilterFunction provides a mechanism for requesting protected resources
    //  * by using an OAuth2AuthorizedClient and including the associated OAuth2AccessToken as a Bearer Token.
    //  * 简单来说使用webclient时候只要传入令牌就可以使用该已注册客户端
    //  * @param authorizedClientManager
    //  * @return
    //  */
    // @Bean
    // WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
    //     ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
    //             new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
    //     return WebClient.builder()
    //             .apply(oauth2Client.oauth2Configuration())
    //             .build();
    // }
    //
    // /**
    //  * see https://docs.spring.io/spring-security/reference/servlet/oauth2/client/core.html#oauth2Client-authorized-manager-provider
    //  * @param clientRegistrationRepository
    //  * @param authorizedClientRepository
    //  * @return
    //  */
    // @Bean
    // public OAuth2AuthorizedClientManager authorizedClientManager(
    //         ClientRegistrationRepository clientRegistrationRepository,
    //         OAuth2AuthorizedClientRepository authorizedClientRepository) {
    //
    //     OAuth2AuthorizedClientProvider authorizedClientProvider =
    //             OAuth2AuthorizedClientProviderBuilder.builder()
    //                     .authorizationCode()
    //                     .refreshToken()
    //                     .clientCredentials()
    //                     .password()
    //                     .build();
    //
    //     DefaultOAuth2AuthorizedClientManager authorizedClientManager =
    //             new DefaultOAuth2AuthorizedClientManager(
    //                     clientRegistrationRepository, authorizedClientRepository);
    //     authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
    //     authorizedClientManager.setAuthorizationSuccessHandler(
    //             (authorizedClient, principal, attributes) -> System.out.println(authorizedClient.toString() + principal + attributes));
    //     authorizedClientManager.setAuthorizationFailureHandler(
    //             (authorizationException, principal, attributes) -> System.out.println(authorizationException.toString() + principal + attributes));
    //
    //     return authorizedClientManager;
    // }



    @Bean
    WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        return WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .build();
    }

    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();
        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
