package com.xjjlearning.database.mysql.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //链式 return this
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false) //不是数据库字段
    private String ignoreColumn = "ignoreColumn";
    @TableField(exist = false)
    private Integer count;
}
