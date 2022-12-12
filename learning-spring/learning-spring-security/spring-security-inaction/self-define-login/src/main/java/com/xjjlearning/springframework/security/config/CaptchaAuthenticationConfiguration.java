package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.authentication.CaptchaAuthenticationProvider;
import com.xjjlearning.springframework.security.filter.CaptchaAuthenticationFilter;
import com.xjjlearning.springframework.security.service.CaptchaCacheStorage;
import com.xjjlearning.springframework.security.service.CaptchaService;
import com.xjjlearning.springframework.security.service.impl.CaptchaCacheStorageImpl;
import com.xjjlearning.springframework.security.service.impl.CaptchaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.Collections;

@Slf4j
// @Configuration
public class CaptchaAuthenticationConfiguration {

    /**
     * spring cache 管理验证码的生命周期.
     *
     * @return the captcha cache storage
     */
    @Bean
    CaptchaCacheStorage captchaCacheStorage() {
        return new CaptchaCacheStorageImpl();
    }

    /**
     * 验证码服务.
     * 两个功能： 发送和校验.
     *
     * @param captchaCacheStorage the captcha cache storage
     * @return the captcha service
     */
    @Bean
    public CaptchaService captchaService(CaptchaCacheStorage captchaCacheStorage) {
        return new CaptchaServiceImpl(captchaCacheStorage);
    }

    /**
     * 自行实现根据手机号查询可用的用户，这里简单举例.
     * 注意该接口可能出现多态。所以最好加上注解@Qualifier
     *
     * @return the user details service
     */
    @Bean
    @Qualifier("captchaUserDetailsService")
    public UserDetailsService captchaUserDetailsService() {
        // 验证码登陆后密码无意义了但是需要填充一下
        return username -> User.withUsername(username).password("TEMP")
                //todo  这里权限 你需要自己注入
                .authorities(AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_APP")).build();
    }

    /**
     * 验证码认证器.
     *
     * @param captchaService     the captcha service
     * @param userDetailsService the user details service
     * @return the captcha authentication provider
     */
    @Bean
    public CaptchaAuthenticationProvider captchaAuthenticationProvider(CaptchaService captchaService,
                                                                       @Qualifier("captchaUserDetailsService")
                                                                               UserDetailsService userDetailsService) {
        return new CaptchaAuthenticationProvider(userDetailsService, captchaService);
    }

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 验证码认证过滤器.
     *
     * @param captchaAuthenticationProvider the captcha authentication provider
     * @return the captcha authentication filter
     */
    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(
                                                                   AuthenticationSuccessHandler authenticationSuccessHandler,
                                                                   AuthenticationFailureHandler authenticationFailureHandler,
                                                                   CaptchaAuthenticationProvider captchaAuthenticationProvider) {
        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        // 配置 authenticationManager
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(captchaAuthenticationProvider));
        captchaAuthenticationFilter.setAuthenticationManager(providerManager);
        // 成功处理器
        captchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 失败处理器
        captchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return captchaAuthenticationFilter;
    }
}