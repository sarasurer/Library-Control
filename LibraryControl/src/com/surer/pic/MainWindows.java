/*
package com.surer.pic;

import java.sql.*;

public class MainWindows {
    public static void establishConnection() throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pst;

        try {
            String sql = "SELECT Author FROM books";

            String username = "CERVIDAESJ/Jiyan Sürer";
            String password = "jyn123";

            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=library;";

            Class.forName("com.surer.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(url, username, password);

            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

 */
