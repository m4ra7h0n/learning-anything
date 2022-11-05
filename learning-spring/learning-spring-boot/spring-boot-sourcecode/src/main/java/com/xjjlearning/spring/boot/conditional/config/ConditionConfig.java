package com.xjjlearning.spring.boot.conditional.config;

import com.xjjlearning.spring.boot.conditional.GPConditional;
import com.xjjlearning.spring.boot.conditional.classes.RequiredBean;
import com.xjjlearning.spring.boot.conditional.classes.SpringService;
import com.xjjlearning.spring.boot.conditional.classes.SpringServiceRequiredOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
public class ConditionConfig {

    @Bean
    @Conditional(value = GPConditional.class)
    public SpringService springService(){
        return new SpringService();
    }

    @Bean
    @ConditionalOnBean(value = RequiredBean.class)
    //加载SpringServiceRequiredOnBean需要有RequiredBean
    public SpringServiceRequiredOnBean springServiceRequiredOnBean(){
        return new SpringServiceRequiredOnBean();
    }
}
