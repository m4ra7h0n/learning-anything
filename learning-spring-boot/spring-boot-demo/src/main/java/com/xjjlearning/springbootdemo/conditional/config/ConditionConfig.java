package com.xjjlearning.springbootdemo.conditional.config;

import com.xjjlearning.springbootdemo.conditional.GPConditional;
import com.xjjlearning.springbootdemo.conditional.classes.RequiredBean;
import com.xjjlearning.springbootdemo.conditional.classes.SpringServiceRequiredOnBean;
import com.xjjlearning.springbootdemo.conditional.classes.SpringService;
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
