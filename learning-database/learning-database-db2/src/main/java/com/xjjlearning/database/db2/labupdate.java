package com.xjjlearning.database.db2;

/**********************************************************************/
/* labupdate.java                                                     */
/* Sample Java program for "DB2 UDB PROGRAMMING USING JAVA"           */
/*                          ( CG11 )                                  */
/*                                                                    */
/*                                                                    */
/* Last update = 01/01/2003                                           */
/*                                                                    */
/**********************************************************************/
/*  Notes:                                                            */
/*                                                                    */
/*  Update the salary of all the employees of a department.           */
/*                                                                    */
/*  This program is intended to be completed with the lab guide       */
/*  as a reference.  The lab guide is the set of instructions that    */
/*  should be followed.  The comments in this program are intended    */
/*  to clarify statements made in the lab guide.                      */
/**********************************************************************/

/*********************??????????????????*******************************/
/*  ( 1 ) Import Java Classes                                         */
/**********************************************************************/
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;



/**********************************************************************/
/* Class definition                                                   */
/**********************************************************************/
public class labupdate
{

    public static void main( String args[]) throws Exception{

        String driver = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://127.0.0.1:50000/testdb";
        String userName = "test";
        String passWord = "123456";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = null;



        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connect completed");
            sql = "UPDATE UDBA.\"staff\" SET SALARY = SALARY * 1.05 WHERE DEPT = ?";


            conn.setAutoCommit(false);

            System.out.println("This program will update the salaries for a department");
            System.out.println("\n");
            System.out.println("Please enter a department name: \n ");

            String str;
            while (true) {
                str = JOptionPane.showInputDialog(null, "输入一个部门类型","输入对话框", JOptionPane.PLAIN_MESSAGE);
                String sqlCheck = "SELECT ID from UDBA.\"staff\" where DEPT = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sqlCheck);
                preparedStatement.setString(1, str);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "修改成功","返回结果", JOptionPane.QUESTION_MESSAGE);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "无效部门","返回结果", JOptionPane.QUESTION_MESSAGE);
                }
            }

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, str);
            int updateCount = preparedStatement.executeUpdate();

            System.out.println("\nNumber of rows updated: " + updateCount);
            conn.commit();
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage() );
        }
    } // end main


}  // end of kegstaff class




