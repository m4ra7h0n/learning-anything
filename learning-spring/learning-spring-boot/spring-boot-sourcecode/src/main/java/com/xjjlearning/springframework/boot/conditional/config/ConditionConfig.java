package com.xjjlearning.springframework.boot.conditional.config;

import com.xjjlearning.springframework.boot.conditional.GPConditional;
import com.xjjlearning.springframework.boot.conditional.classes.RequiredBean;
import com.xjjlearning.springframework.boot.conditional.classes.SpringService;
import com.xjjlearning.springframework.boot.conditional.classes.SpringServiceRequiredOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
// initial this config when having the property xjj.redis and having "enable:true"
@ConditionalOnProperty(prefix = "xjj.redis", name = "enable", havingValue = "true")
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
