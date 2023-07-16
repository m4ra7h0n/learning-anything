package com.xjjlearning.hack.springboot.thymeleafdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * Created by xjj on 2023/4/22.
 */
// 矩阵变量配置
//@Configuration(proxyBeanMethods = false)
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
////        不移除矩阵变量分号后的内容
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
//}
