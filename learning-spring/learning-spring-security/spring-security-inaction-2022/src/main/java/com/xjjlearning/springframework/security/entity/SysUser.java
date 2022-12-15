package com.xjjlearning.springframework.security.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * created by xjj on 2022/12/13
 */
@Data
public class SysUser {
    @Id
    Long userId;
    String username;
    String encodePassword;
    Boolean expired;
    Boolean locked;
    Boolean enabled;
}
