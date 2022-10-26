package com.xjjlearning.database.mysql.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建信息
 */
@Data
public class CreateInfo implements Serializable {
	private static final long serialVersionUID = 8275281589408844992L;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
}
