package com.xjjlearning.apache.tomcat.dbcp;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * Created by xjj on 2023/5/9.
// */
//public class BasicDataSourceDemo {
//    public static void main(String[] args) throws Exception {
//        // combine the commons-dbcp2 and commons-pool2
//
//        // method1
//        Properties p = new Properties();
//        p.load(BasicDataSourceDemo.class.getResourceAsStream("dbcp.properties"));
//        BasicDataSource poolFromFactory = BasicDataSourceFactory.createDataSource(p);
//        Connection connection = poolFromFactory.getConnection();
//
//        // method2
//        BasicDataSource pool = new BasicDataSource();
//        pool.setUsername("");
//        pool.setPassword("");
//        pool.setDriverClassName("");
//        pool.setUrl("");
//        Connection connection2 = pool.getConnection();
//    }
//}
