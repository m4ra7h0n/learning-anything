package com.xjjlearning.springframework.security.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * created by xjj on 2022/12/13
 */
@Data
public class UserRole {
    @Id
    Long userRoleId;
    String username;
    String roleId;
    String roleName;
    Boolean enabled;
}
