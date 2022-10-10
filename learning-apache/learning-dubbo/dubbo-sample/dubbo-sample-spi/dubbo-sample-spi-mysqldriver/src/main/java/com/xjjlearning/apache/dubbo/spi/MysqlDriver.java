package com.xjjlearning.apache.dubbo.spi;

public class MysqlDriver implements Driver {
    @Override
    public String connect() {
        return "连接mysql数据库";
    }
}
