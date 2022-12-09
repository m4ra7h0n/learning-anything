# openid connect (OCId)
https://openid.net/connect/
UserDetailsServiceAutoConfiguration
定义用户加载到内存的逻辑, 使用配置文件账户密码逻辑  

# 一个用来做测试的包 用来动态获取网络数据(selenium)
selenium:
page factory makes locate easily
design pattern: A java class is created that corresponds to each web page

# spring security 在 spring 中的生命周期
// java-privilege                -> nextFilter and isAsync and java-privilege-> 1.isSkipAsyncDispatch 2.isAlreadyFiltered
ApplicationFilterChain(doFilter) -> ApplicationFilterChain(internalDoFilter) -> OncePerRequestFilter(doFilter)
-> CharacterEncodingFilter(doFilterInternal) ->
ApplicationFilterChain(doFilter) -> ApplicationFilterChain(internalDoFilter) -> OncePerRequestFilter(doFilter)
-> ....(2) ->
ApplicationFilterChain(doFilter) -> ApplicationFilterChain(internalDoFilter) -> DelegatingFilterProxy(doFilter)
filterProxyChain(invokeDelegate) -> spring-security-filters(doFilter) 
.
.
.
or simplify

ApplicationFilterChain(spring):
filter0
filter1
filter2
delegatingFilterProxy[filterProxyChain] -> securityFilterChain
filter4
filter5

Security filter chain: [
    WebAsyncManagerIntegrationFilter
    SecurityContextPersistenceFilter
    HeaderWriterFilter
    CsrfFilter
    LogoutFilter
    UsernamePasswordAuthenticationFilter
    DefaultLoginPageGeneratingFilter
    DefaultLogoutPageGeneratingFilter
    BasicAuthenticationFilter
    RequestCacheAwareFilter
    SecurityContextHolderAwareRequestFilter
    AnonymousAuthenticationFilter
    SessionManagementFilter
    ExceptionTranslationFilter
    AuthorizationFilter
]

