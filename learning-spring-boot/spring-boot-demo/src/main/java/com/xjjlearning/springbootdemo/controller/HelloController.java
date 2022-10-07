package com.xjjlearning.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @Autowired
//    RedisTemplate<String, String> redisTemplate;
//
//    @GetMapping("/hello")
//    public String hello(){
//        redisTemplate.opsForValue().set("key", "value");
//        return "hello";
//    }
}
