# 是一种过时的技术 但是springsecurity源码层面没有变 学习只是为了了解原理 

SecurityAutoConfiguration 实现自动装配  
其中Import如下四个配置类, 组织分散的@Configuration

```text
SpringBootWebSecurityConfiguration.class
WebSecurityEnablerConfiguration.class
SecurityDataConfiguration.class
ErrorPageSecurityFilterConfiguration.class
```