package com.xjjlearning.database.mysql.model;

import lombok.Data;

/**
 * 用户角色关联表
 */
@Data
public class SysUserRole {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;
}
