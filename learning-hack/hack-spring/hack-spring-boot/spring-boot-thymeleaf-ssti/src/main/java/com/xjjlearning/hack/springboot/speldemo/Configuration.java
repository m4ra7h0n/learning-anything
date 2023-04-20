package com.xjjlearning.hack.springboot.speldemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by xjj on 2023/4/20.
 */
@ComponentScan
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public String name() {
        return "xjj";
    }
    @Bean
    public String msg() {
        return "How are you today?";
    }
}
