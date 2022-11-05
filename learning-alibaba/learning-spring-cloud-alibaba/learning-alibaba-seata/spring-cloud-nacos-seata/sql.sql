-- 创建 order库、业务表、undo_log表
create database seata_order;
use seata_order;

DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` varchar(255) DEFAULT NULL,
                             `commodity_code` varchar(255) DEFAULT NULL,
                             `count` int(11) DEFAULT 0,
                             `money` int(11) DEFAULT 0,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     BIGINT(20)   NOT NULL,
    `xid`           VARCHAR(100) NOT NULL,
    `context`       VARCHAR(128) NOT NULL,
    `rollback_info` LONGBLOB     NOT NULL,
    `log_status`    INT(11)      NOT NULL,
    `log_created`   DATETIME     NOT NULL,
    `log_modified`  DATETIME     NOT NULL,
    `ext`           VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


-- 创建 stock库、业务表、undo_log表
create database seata_stock;
use seata_stock;

DROP TABLE IF EXISTS `stock_tbl`;
CREATE TABLE `stock_tbl` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `commodity_code` varchar(255) DEFAULT NULL,
                             `count` int(11) DEFAULT 0,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY (`commodity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     BIGINT(20)   NOT NULL,
    `xid`           VARCHAR(100) NOT NULL,
    `context`       VARCHAR(128) NOT NULL,
    `rollback_info` LONGBLOB     NOT NULL,
    `log_status`    INT(11)      NOT NULL,
    `log_created`   DATETIME     NOT NULL,
    `log_modified`  DATETIME     NOT NULL,
    `ext`           VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 初始化库存模拟数据
INSERT INTO seata_stock.stock_tbl (id, commodity_code, count) VALUES (1, 'product-1', 9999999);
INSERT INTO seata_stock.stock_tbl (id, commodity_code, count) VALUES (2, 'product-2', 0);
