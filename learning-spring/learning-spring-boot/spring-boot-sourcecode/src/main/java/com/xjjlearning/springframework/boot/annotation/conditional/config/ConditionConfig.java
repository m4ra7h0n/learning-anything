package com.xjjlearning.springframework.boot.annotation.conditional.config;

import com.xjjlearning.springframework.boot.annotation.conditional.classes.RequiredBean;
import com.xjjlearning.springframework.boot.annotation.conditional.classes.SpringService;
import com.xjjlearning.springframework.boot.annotation.conditional.classes.SpringServiceRequiredOnBean;
import com.xjjlearning.springframework.boot.annotation.conditional.GPConditional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
public class ConditionConfig {
    //一旦注释该bean springServiceRequiredOnBean就无法加载了
    @Bean
    public RequiredBean requiredBean() {
        return new RequiredBean();
    }

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
