package com.xjjlearning.springframework.security.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * created by xjj on 2022/12/13
 */
@Data
public class Role {
    @Id
    Long roleId;
    String roleName;
    String roleComment;
    Boolean enabled;
}
