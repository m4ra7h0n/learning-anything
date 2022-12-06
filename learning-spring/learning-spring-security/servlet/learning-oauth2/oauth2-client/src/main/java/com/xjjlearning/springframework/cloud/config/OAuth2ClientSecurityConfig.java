package com.xjjlearning.springframework.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class OAuth2ClientSecurityConfig {

	// @Bean
	// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.oauth2Client(oauth2 -> oauth2
		// 		.clientRegistrationRepository(this.clientRegistrationRepository())
		// 		.authorizedClientRepository(this.authorizedClientRepository())
		// 		.authorizedClientService(this.authorizedClientService())
		// 		.authorizationCodeGrant(codeGrant -> codeGrant
		// 			.authorizationRequestRepository(this.authorizationRequestRepository())
		// 			.authorizationRequestResolver(this.authorizationRequestResolver())
		// 			.accessTokenResponseClient(this.accessTokenResponseClient())
		// 		)
		// 	);
		// return http.build();
	// }
}