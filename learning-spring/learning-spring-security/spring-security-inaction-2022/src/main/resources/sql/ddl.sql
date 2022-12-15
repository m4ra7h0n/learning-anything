drop schema if exists `spring_security`;
create schema `spring_security`;
use `spring_security`;
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`         int(64) unsigned NOT NULL AUTO_INCREMENT,
    `username`        varchar(128)  NOT NULL unique,
    `encode_password` varchar(1024) NOT NULL,
    `expired`         tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户是否过期',
    `locked`          tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户是否锁定',
    `enabled`          tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户是否可用',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `role_id`      varchar(64)  NOT NULL COMMENT '主键ID',
    `role_name`    varchar(32)  NOT NULL UNIQUE COMMENT '角色标识',
    `role_comment` varchar(500) NOT NULL COMMENT '说明',
    `enabled`      tinyint(1)   DEFAULT 1 COMMENT '是否可用',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT = '角色表';

DROP TABLE IF EXISTS `user_role`;
create table `user_role`
(
    `user_role_id` varchar(64) NOT null COMMENT '主键ID',
    `username`     varchar(64) NOT null COMMENT '用户名',
    `role_id`      varchar(64) NOT null COMMENT '角色ID',
    `role_name`    varchar(32) NOT NULL COMMENT '用户标识',
    `enabled`      tinyint(1) DEFAULT '1' COMMENT '是否可用',
    PRIMARY KEY (`user_role_id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT '用户角色表';