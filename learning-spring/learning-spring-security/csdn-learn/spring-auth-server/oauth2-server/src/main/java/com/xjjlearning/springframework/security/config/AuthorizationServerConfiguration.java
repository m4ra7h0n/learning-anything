package com.xjjlearning.springframework.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.KeyStore;
import java.util.UUID;

/**
 * created by xjj on 2022/12/14
 */
@Configuration
public class AuthorizationServerConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer<>();
        // TODO 你可以根据需求对authorizationServerConfigurer进行一些个性化配置
        RequestMatcher authorizationServerEndpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

        // ①
        http.requestMatcher(authorizationServerEndpointsMatcher)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                // ②忽略掉相关端点的csrf
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(authorizationServerEndpointsMatcher))
                // 开启form登录
                .formLogin()
                .and()
                // ③应用 授权服务器的配置
                .apply(authorizationServerConfigurer);
        return http.build();
    }


    @SneakyThrows
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
        // todo 写一个接口把他封装起来
        // RegisteredClient xjj = registeredClientRepository.findByClientId("xjj");
        final String id = "10000";
        RegisteredClient registeredClient = registeredClientRepository.findById(id);
        if (registeredClient == null) {
            registeredClient = this.createRegisteredClient("1000");
            registeredClientRepository.save(registeredClient);
        }
        return registeredClientRepository;
    }


    private RegisteredClient createRegisteredClient(final String id) {
        return RegisteredClient.withId(UUID.randomUUID().toString())
//               客户端ID
                .clientId("xjj")
//               此处为了避免频繁启动重复写入仓库
                .id(id)
//                client_secret_basic 模式下的密码  在客户端需要存明文 在授权服务器存密文
                .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .encode("secret"))
//                名称可不定义
                .clientName("xjj")
//                授权方法
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                支持的授权类型
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                回调地址名单，不在此列将被拒绝 而且只能使用IP或者域名  不能使用 localhost
                .redirectUri("http://127.0.0.1:8082/login/oauth2/code/xjj-client-oidc")
                .redirectUri("http://127.0.0.1:8082/authorized")
                .redirectUri("http://127.0.0.1:8082/login/oauth2/code/xjj")
                .redirectUri("http://127.0.0.1:8082/foo/bar")
                .redirectUri("https://baidu.com")
//                OIDC支持
                .scope(OidcScopes.OPENID)
//                其它Scope
                .scope("message.read")
                .scope("USERINFO")
                .scope("message.write")
//                JWT的配置项 包括TTL  是否复用refreshToken等等
                .tokenSettings(TokenSettings.builder().build())
//                配置客户端相关的配置项，包括验证密钥或者 是否需要授权页面
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true).build())
                .build();
    }

    /**
     * 加载JWK资源, 必加
     *
     * @return the jwk source
     */
    @SneakyThrows
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        //TODO 这里优化到配置
        String path = "jose.jks";
        String alias = "jose";
        String pass = "xjjjjj";

        ClassPathResource resource = new ClassPathResource(path);
        KeyStore jks = KeyStore.getInstance("jks");
        char[] pin = pass.toCharArray();
        jks.load(resource.getInputStream(), pin);
        RSAKey rsaKey = RSAKey.load(jks, alias, pin);

        JWKSet jwkSet = new JWKSet(rsaKey);  // jwtSet封装jwk 且为singletonList
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public ProviderSettings providerSettings(@Value("${server.port}") Integer port) {
        //TODO 生产应该使用域名
        return ProviderSettings.builder()
                .issuer("http://localhost:" + port)
                // .jwkSetEndpoint("")
                .build();
    }

}
