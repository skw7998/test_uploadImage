//JDBCUtils.java
package com.sk7998.uploadimage.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {



    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        Connection  conn = null;
        try {
            String url = "jdbc:mysql://rm-cn-lbj3mj9wy000cf3o.rwlb.rds.aliyuncs.com:3306/test_1?serverTimezone=UTC";
            String username = "user_sk";
            String password = "Wsk@10615";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return conn;
    }


    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}


