package com.xjjlearning.database.db2;


/**********************************************************************/
/*                                                                    */
/* Sample Java program for "DB2 UDB PROGRAMMING USING JAVA"           */
/*                          ( CG11 )                                  */
/*                                                                    */
/*                                                                    */
/* Last update = 01/31/2000                                           */
/*                                                                    */
/**********************************************************************/
/*  Notes:                                                            */
/*                                                                    */
/*  This program is intended to be completed with the lab guide       */
/*  as a reference.  The lab guide is the set of instructions that    */
/*  should be followed.  The comments in this program are intended    */
/*  to clarify statements made in the lab guide.                      */
/**********************************************************************/

/**********************************************************************/
/* Import Java Classes                                                 */
/**********************************************************************/
import java.sql.*;
import sqlj.runtime.*;
import sqlj.runtime.ref.*;
import java.io.*;
import java.util.*;


public class clob
{
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
        String driver = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://127.0.0.1:50000/testdb";
        String userName = "test";
        String passWord = "123456";

        Connection conn = null;
        ResultSet resultSet = null;
        String sql = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, passWord);


//            sql = "insert into UDBA.\"clob\" (ID, NAME, PIC) values (1, 'BIGTXT', ?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            File file = new File("/Users/xjj/IdeaProjects/learning-anything/learning-database/learning-database-db2/src/main/resources/BIGTXT");
//            FileInputStream fis = new FileInputStream(file);
//            InputStreamReader inputStreamReader = new InputStreamReader(fis);
////            statement.setAsciiStream(1, fis, (int)file.length());
//            statement.setCharacterStream(1, inputStreamReader);
//            statement.executeUpdate();

            sql = "SELECT * FROM UDBA.\"clob\" WHERE NAME = 'BIGTXT'";
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int ID = resultSet.getInt(1);
                String NAME = resultSet.getString(2);
                Clob PIC = resultSet.getClob(3);
                System.out.println(PIC.length());
            }
            statement.close();
            conn.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}  // end of javalob class


