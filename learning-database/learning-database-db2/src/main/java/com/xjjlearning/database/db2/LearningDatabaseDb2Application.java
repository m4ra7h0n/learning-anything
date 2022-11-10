package com.xjjlearning.database.db2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

//@SpringBootApplication
public class LearningDatabaseDb2Application {
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
                "\n ID       NAME      SALARY\n";
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
            sql = "SELECT ID, NAMi, SALARY FROM UDBA.\"staff\" where DEPT = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            conn.setAutoCommit(true);

            System.out.println( intext );
            System.out.println( indash );

//            sql = "SELECT ID, NAME, JOB, SALARY FROM UDBA.\"staff\" where ID = 10";
            preparedStatement.setString(1, "JAVA");
            rs = preparedStatement.executeQuery();

            SQLWarning SQLWarn;
            if ((SQLWarn = preparedStatement.getWarnings()) != null) {
                System.out.println ("\n Value of SQLWarn on single row insert to DEP is: \n");
                System.out.println (SQLWarn);
            }
            while(rs.next()) {
                String id = rs.getString(1);
                name = rs.getString(2);
                salary = rs.getString(4);
                String outline = (id + blanks.substring(0, 10 - id.length())) +
                        (name + blanks.substring(0, 10 - name.length()))   +
                        (salary + blanks.substring(0, 12 - salary.length()));
                System.out.println("\n" + outline);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage() );
        }

    }

}
