package com.xjjlearning.springframework.boot.conditional.config;

import com.xjjlearning.springframework.boot.conditional.classes.MissingBeanClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by xjj on 2022/12/10
 */
@Component  //必须加 除非定义spring.factories
@ConditionalOnClass(MissingBeanClass.class)
@ConditionalOnMissingBean(MissingBeanClass.class)
// @Component(proxyBeanMethod = false) // make none proxy (multi value)
public class ConditionOnMissing {
    @Bean
    String DefaultBean() {
        return "hello";
    }

    @ConditionalOnClass(MissingBeanClass.class)
    static class Beans {

    }
}
