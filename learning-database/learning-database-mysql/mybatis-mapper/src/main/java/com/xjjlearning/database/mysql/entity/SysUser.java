package com.xjjlearning.database.mysql.entity;

import io.mybatis.provider.Entity;
import lombok.Data;

@Data
@Entity.Table("sys_user")
public class SysUser {
    @Entity.Column(id = true)
    private Long id;
    @Entity.Column
    private String username;
    @Entity.Column
    private String encodePassword;
    @Entity.Column
    private Integer age;
}
