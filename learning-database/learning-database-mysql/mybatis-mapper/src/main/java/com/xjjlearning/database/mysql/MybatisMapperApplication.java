package com.xjjlearning.database.mysql;

import com.xjjlearning.database.mysql.service.SysUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MybatisMapperApplication implements CommandLineRunner {
    @Resource
    SysUserService sysUserService;

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sysUserService.select("xjj");
    }
}
