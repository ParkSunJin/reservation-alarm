package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;

import java.sql.*;

public class UserH2Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // h2 드라이버를 로딩
        Class.forName("org.h2.Driver");
        // 데이터베이스와 연결(Connection 객체 생성)
        Connection c = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/reservation-alarm", "sa", "");

        PreparedStatement ps = c.prepareStatement("select * from users");

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setUserId(rs.getString("id"));
        user.setUserName(rs.getString("name"));
        user.setUserPassword(rs.getString("password"));
        System.out.println(user.toString());

        rs.close();
        ps.close();
        c.close();
    }
}
