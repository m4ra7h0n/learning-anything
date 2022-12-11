package com.xjjlearning.springframework.security.config;

import com.xjjlearning.springframework.security.filter.JsonLoginPostProcessor;
import com.xjjlearning.springframework.security.filter.LoginPostProcessor;
import com.xjjlearning.springframework.security.filter.PreLoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
     *
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
                    .and()
                    /**
                     * 1.通过增加前置过滤器实现账户密码的定制
                     */
                    // 增加一层过滤 通过传入的type和内容 来解码用户名和密码 再交给 下一个filter
                    .addFilterBefore(preLoginFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                    // // 这个配置使用后 前台的登录提交表单逻辑 会交给这个url, 实际上没有写任何逻辑 然后会交给 UsernamePasswordAuthenticationFilter
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
                    .successForwardUrl("/login/success").failureForwardUrl("/login/failure");

                    /**
                     * 2.通过继承 并修改 UsernamePasswordAuthenticationFilter 实现定制
                     * 暂时未实现
                     */
                    // .addFilter(multiTypeUsernamePasswordAuthenticationFilter)
                    // .formLogin()
                    // .loginProcessingUrl(LOGIN_PROCESSING_URL);
        }
    }
}
