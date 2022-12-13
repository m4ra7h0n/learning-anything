package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.filter.JsonLoginPostProcessor;
import com.xjjlearning.springframework.security.filter.JwtAuthenticationFilter;
import com.xjjlearning.springframework.security.filter.LoginPostProcessor;
import com.xjjlearning.springframework.security.filter.PreLoginFilter;
import com.xjjlearning.springframework.security.handler.CustomLogoutHandler;
import com.xjjlearning.springframework.security.handler.CustomLogoutSuccessHandler;
import com.xjjlearning.springframework.security.handler.SimpleAccessDeniedHandler;
import com.xjjlearning.springframework.security.handler.SimpleAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * created by xjj on 2022/12/11
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSpringBootWebSecurityConfiguration {
    private static final String LOGIN_PROCESSING_URL = "/process";

    /**
     * Json login post processor json login post processor.
     *
     * @return the json login post processor
     */
    @Bean
    public JsonLoginPostProcessor jsonLoginPostProcessor(){
        return new JsonLoginPostProcessor();
    }

    /**
     * Pre login filter pre login filter.
     * 只注入了除 default 外的 JsonLoginPostProcessor, 而默认的直接会被加载进去
     * @param loginPostProcessors the login post processors
     * @return the pre login filter
     */
    @Bean
    public PreLoginFilter preLoginFilter(Collection<LoginPostProcessor> loginPostProcessors){
        return new PreLoginFilter(LOGIN_PROCESSING_URL, loginPostProcessors);
    }


    /**
     * The type Default configurer adapter.
     */
    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    static class DefaultConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Resource
        private PreLoginFilter preLoginFilter;

        @Resource
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        // @Resource
        // private CaptchaAuthenticationFilter captchaAuthenticationFilter;

        @Resource
        private AuthenticationSuccessHandler authenticationSuccessHandler;

        @Resource
        private AuthenticationFailureHandler authenticationFailureHandler;

        @Resource
        private SimpleAccessDeniedHandler simpleAccessDeniedHandler;

        @Resource
        private SimpleAuthenticationEntryPoint simpleAuthenticationEntryPoint;

        @Resource
        private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

        @Resource
        private AccessDecisionManager accessDecisionManager;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // AuthenticationConfiguration.getAuthenticationManager()
            super.configure(auth);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .cors()
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    // 动态权限开启
                    //.withObjectPostProcessor(filterSecurityInterceptorObjectPostProcessor())
                    .and()
                    /**
                     * 异常处理, 设置异常处理之后 服务器返回的状态码是200 而我们的Handler处理这个状态
                     */
                    .exceptionHandling()
                    .authenticationEntryPoint(simpleAuthenticationEntryPoint) // 401 unauthorized
                    .accessDeniedHandler(simpleAccessDeniedHandler) // 403 forbidden
                    .and()
                    /**
                     * 验证码过滤器
                     */
                    // .addFilterBefore(captchaAuthenticationFilter, PreLoginFilter.class)
                    /**
                     * jwt 验证过滤器, 设置session无状态
                     */
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    /**
                     * 1.通过增加前置过滤器实现账户密码的定制
                     */
                    // 增加一层过滤 通过传入的type和内容 来解码用户名和密码 再交给 下一个filter
                    .addFilterBefore(preLoginFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                    // // 这个配置使用后 前台的登录提交表单逻辑 会交给这个url, 实际上没有写任何逻辑 然后会交给 UsernamePasswordAuthenticationFilter
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
                    // 登录 服务端返回状态码是200 具体逻辑我们自定义 -> 成功后返回jwt token  失败后返回 错误信息
                    .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                    .and()
                    // 登录成功失败的跳转url 和上面的只能选一个
                    // .successForwardUrl("/login/success").failureForwardUrl("/login/failure")
                    .logout().addLogoutHandler(new CustomLogoutHandler()).logoutSuccessHandler(new CustomLogoutSuccessHandler());


                    /**
                     * 2.通过继承 并修改 UsernamePasswordAuthenticationFilter 实现定制
                     * 暂时未实现
                     */
                    // .addFilter(multiTypeUsernamePasswordAuthenticationFilter)
                    // .formLogin()
                    // .loginProcessingUrl(LOGIN_PROCESSING_URL);
        }

        /**
         * 自定义 FilterSecurityInterceptor  ObjectPostProcessor 以替换默认配置达到动态权限的目的
         *
         * @return ObjectPostProcessor
         */
        private ObjectPostProcessor<FilterSecurityInterceptor> filterSecurityInterceptorObjectPostProcessor() {
            return new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                    object.setAccessDecisionManager(accessDecisionManager);
                    object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                    return object;
                }
            };
        }
    }
}
