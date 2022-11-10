package com.xjjlearning.database.db2;

/**********************************************************************/
/* labstaff.java                                                      */
/* Sample Java program for "DB2 UDB PROGRAMMING USING JAVA"           */
/*                          ( CG11 )                                  */
/*                                                                    */
/*                                                                    */
/* Last update = 01/01/2003                                           */
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
/* Import Java Classes                                                */
/**********************************************************************/
import java.sql.*;
import java.io.*;
import java.util.*;
import java.math.*;



/**********************************************************************/
/* Class definition                                                   */
/**********************************************************************/
public class labstaff
{
/**********************************************************************/
    /* Register the class with the db2 Driver                             */
    /**********************************************************************/
    static
    {   try {
        Class.forName ("com.ibm.db2.jcc.DB2Driver");
        }
    catch (Exception e)
    {   System.out.println ("\n  Error loading DB2 Driver...\n");
        System.out.println (e);
        System.exit(1);
    }
    }

/**********************************************************************/
    /* Main routine                                                       */
    /**********************************************************************/
    public static void main( String args[]) throws Exception
    {

///*********************** ????????????????? ****************************/
//        /* Define variable declarations for the variable which will be used   */
//        /* to pass data to and from the stored procedure:                     */
//        /* A character string for passing the department in.                  */
//        /* A double for returning the median salary.                          */
//        /* An integer for returning the number of employees.                  */
///**********************************************************************/
//        String name = "";
//        java.lang.String deptno  = "";
//        short id = 0;
//        String salary = " ";
//        String job = "";
//        short NumEmp = 0;
///***********************************************************************/
//        /* Header line                                                         */
///***********************************************************************/
        String  intext =
                "\n NAME     JOB       SALARY\n";
        String  indash =
                "--------  --------  --------------\n";
        String blanks = "                                                        ";
//
//        BufferedReader in = new BufferedReader( new InputStreamReader (System.in));
//        String s;
//
//        /*  Establish connection and set default context  */
//        System.out.println("Connect statement follows:");
//        /***************** ????????????? *********************/
//        /* ( 1 ) Code a Connect statement                    */
//        /*****************************************************/
//   ?????????? ?????? = ????????????.???????????("????:???:??????","udba","udba");
//        sample.setAutoCommit(false);
//
//        /**************************************************/
//        /*  Print instruction lines                       */
//        /* Write out the header line                      */
//        /*                                                */
//        /**************************************************/
//

//
//
//        /*  Issue Select statement  */
//
//        try
//        {
//
//            /************************ ?????????????????????? ***********************/
//            /* ( 2 ) Create a Statement object name stmt.                          */
//            /*                                                                     */
//            /***********************************************************************/
//     ????????? ???? = ??????.??????????????();
//
//            /********************** ????????????????? ******************************/
//            /* (3) Define the ResultSet object named rs.                           */
//            /*     The ResultSet object rs will use the query                      */
//            /*     SELECT NAME, JOB, SALARY FROM STAFF WHERE ID = 10               */
//            /***********************************************************************/
//
//     ????????? ?? = ????.????????????( "select NAME, JOB, SALARY from staff Where ID = 10");
//
//            /********************* ?????????????????? ******************************/
//            /* ( 4 ) Code a resultset method to move to next row of the resultset  */
//            /***********************************************************************/
//
//            boolean more = ??.????();
//            while ( more ) {
//                name = rs.getString(1);
//                job = rs.getString(2);
//                salary = rs.getString(3);
//                String outline = (name + blanks.substring(0, 10 - name.length())) +
//                        (job + blanks.substring(0, 10 - job.length()))   +
//                        (salary + blanks.substring(0, 12 - salary.length()));
//                System.out.println("\n" + outline);
//                /*************************  ?????????????  **********************/
//                /* ( 5 ) Move to the next row of the resultset                  */
//                /****************************************************************/
//                more = ??.????();
//            }
//
//        }  // end try
//        catch ( SQLException x )
//        {
//            System.out.println("Error on call " + x.getErrorCode()
//                    + " and sqlstate of " + x.getSQLState()  + " message " + x.getMessage() );
//        }
//
//        System.exit(0);
        String driver = "com.ibm.db2.jcc.DB2Driver";
        String url = "jdbc:db2://127.0.0.1:50000/testdb";
        String userName = "test";
        String passWord = "123456";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = null;

        String name;
        String job;
        String salary;


        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connect completed");
            st = conn.createStatement();

            conn.setAutoCommit(true);

            System.out.println( intext );
            System.out.println( indash );

            sql = "SELECT ID, NAME, JOB, SALARY FROM UDBA.\"staff\" where ID = 10";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                name = rs.getString(2);
                job = rs.getString(3);
                salary = rs.getString(4);
                String outline = (name + blanks.substring(0, 10 - name.length())) +
                        (job + blanks.substring(0, 10 - job.length()))   +
                        (salary + blanks.substring(0, 12 - salary.length()));
                System.out.println("\n" + outline);
                /*************************  ?????????????  **********************/
                /* ( 5 ) Move to the next row of the resultset                  */
                /****************************************************************/
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage() );
        }
    } // end main
}  // end of kegstaff class




