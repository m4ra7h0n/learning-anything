package com.xjjlearning.springframework.boot.annotation.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

//基础condition的扩展, Windows环境则加载Bean
public class GPConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("os.name");
        if (!property.contains("Windows")) {
            return true;
        }
        return false;
    }
}
