package com.xjjlearning.database.mysql.c3p0;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCRUD {
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("INSERT INTO users (username,PASSWORD,email,birthday)VALUES('SUN99','123','123456@qq.com','2020-01-01')");
            ps.executeUpdate();
            System.out.println("添加操作执行成功！");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("添加操作执行失败！");
        } finally {
            C3P0Util.release(conn, ps, null);
        }
    }
 
    @Test
    public void testSelect() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("Select * from users");
            rs = ps.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setBirthday(rs.getDate(5));
                list.add(u);
            }
            for (User user : list) {
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, rs);
        }
    }
 
    @Test
    public void testDelete() {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("delete from users where username='SUN99'");
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, null);
        }
    }
 
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("UPDATE users SET username='SUN100',PASSWORD='456'WHERE id='1'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, null);
        }
    }
}