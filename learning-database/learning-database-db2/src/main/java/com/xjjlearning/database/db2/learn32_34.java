package com.xjjlearning.database.db2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

//@SpringBootApplication
public class learn32_34 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
//            Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("no available driver");
//            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String  intext =
                "\n NAME       JOB      SALARY\n";
        String  indash =
                "--------  --------  --------------\n";
        String blanks = "                                                        ";
        String driver = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://127.0.0.1:50000/testdb";
        String userName = "test";
        String passWord = "123456";

        Connection conn = null;
        ResultSet rs = null;
        String sql = null;

        String name;
        String job;
        String salary;


        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connect completed");

            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("数据库名: " + metaData.getDatabaseProductName());
            System.out.println("数据库版本: " + metaData.getDatabaseProductVersion());
            System.out.println("数据库名: " + metaData.getDatabaseProductName());
            conn.setSchema("UDBA");
            String[] tableTypes = {"TABLE"};
            ResultSet udba = metaData.getTables(null, null, "%", tableTypes);
            System.out.println(udba.getFetchSize());

//            while (udba.next()) {
//                String s = rs.getString(1);
//                System.out.println("\nCatalog Name: " + s + " Schema Name: " + rs.getString(2) +
//                        " Table Name: " + rs.getString(3) );
//            }

            conn.setAutoCommit(true);

//            System.out.println( intext );
//            System.out.println( indash );

            String outline;


//            while (rs.next()) {
//                name = rs.getString(2);
//                job = rs.getString(3);
//                salary = rs.getString(4);
//                outline =(name + blanks.substring(0, 10 - name.length()))   +
//                        (job + blanks.substring(0, 10 - job.length())) +
//                        (salary + blanks.substring(0, 12 - salary.length()));
//                System.out.println("\n" + outline);
//
//            }
            conn.close();
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage() );
        }

    }

}

