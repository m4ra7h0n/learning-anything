OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);  
```
	public static void applyDefaultSecurity(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
				new OAuth2AuthorizationServerConfigurer<>();
		RequestMatcher endpointsMatcher = authorizationServerConfigurer
				.getEndpointsMatcher();
		http
			.requestMatcher(endpointsMatcher)
			.authorizeRequests(authorizeRequests ->
				authorizeRequests.anyRequest().authenticated()
			)
			.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
			.apply(authorizationServerConfigurer);
	}
```


```text
OAuth2AuthorizationServerConfigurer
AbstractHttpConfigurer
OAuth2ClientAuthenticationConfigurer
OAuth2AuthorizationEndpointConfigurer
OAuth2TokenEndpointConfigurer
OAuth2TokenIntrospectionEndpointConfigurer
OAuth2TokenRevocationEndpointConfigurer
OidcConfigurer
RegisteredClientRepository
OAuth2AuthorizationService
OAuth2AuthorizationConsentService
NimbusJwkSetEndpointFilter
OAuth2AuthorizationServerMetadataEndpointFilter
```


```text
Security filter chain: [
  WebAsyncManagerIntegrationFilter
  SecurityContextPersistenceFilter
  ProviderContextFilter
  HeaderWriterFilter
  CsrfFilter
  LogoutFilter
  OAuth2AuthorizationEndpointFilter
  OidcProviderConfigurationEndpointFilter
  NimbusJwkSetEndpointFilter
  OAuth2AuthorizationServerMetadataEndpointFilter
  OAuth2ClientAuthenticationFilter
  UsernamePasswordAuthenticationFilter
  DefaultLoginPageGeneratingFilter
  DefaultLogoutPageGeneratingFilter
  RequestCacheAwareFilter
  SecurityContextHolderAwareRequestFilter
  AnonymousAuthenticationFilter
  SessionManagementFilter
  ExceptionTranslationFilter
  FilterSecurityInterceptor
  OAuth2TokenEndpointFilter
  OAuth2TokenIntrospectionEndpointFilter
  OAuth2TokenRevocationEndpointFilter
  OidcUserInfoEndpointFilter
]
```