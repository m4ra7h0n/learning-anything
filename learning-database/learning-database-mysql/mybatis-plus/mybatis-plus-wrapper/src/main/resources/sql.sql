use mybatis_plus;
-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE user
(
    id      BIGINT (20) NOT NULL COMMENT '主键ID',
    name    VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age     INT (11) NULL DEFAULT NULL COMMENT '年龄',
    email   VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    role_id BIGINT (20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id            BIGINT (20) NOT NULL COMMENT '主键ID',
    role_name     VARCHAR(30) NULL DEFAULT NULL COMMENT '角色名',
    role_describe VARCHAR(30) NULL DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (id)
);

DELETE FROM user;
DELETE FROM role;

INSERT INTO role (id, role_name, role_describe)
VALUES (1, '管理员', 'boos 级别'),
       (2, '用户', '就是个普通人'),
       (3, '程序猿', '偶尔需要用来祭天');

INSERT INTO user (id, name, age, email, role_id)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', 1),
       (2, 'Jack', 20, 'test2@baomidou.com', 2),
       (3, 'Tom', 28, 'test3@baomidou.com', 2),
       (4, 'Sandy', 21, 'test4@baomidou.com', 3),
       (5, 'Billie', 24, 'test5@baomidou.com', 3);

