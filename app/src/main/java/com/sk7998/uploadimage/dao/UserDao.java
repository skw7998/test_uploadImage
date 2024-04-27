//UserDao.java
package com.sk7998.uploadimage.dao;


import com.sk7998.uploadimage.entity.UserInfo;
import com.sk7998.uploadimage.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    public boolean login(String name,String pwd){

        String sql = "select * from user_info where name = ? and pwd = ?";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);
            pst.setString(2,pwd);

            if(pst.executeQuery().next()){

                return true;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return false;
    }

    public boolean register(UserInfo user){

        String sql = "insert into user_info(name,pwd,phone) values (?,?,?)";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,user.getName());
            pst.setString(2,user.getPwd());
            pst.setString(3,user.getPhone());

            int value = pst.executeUpdate();

            if(value>0){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }
        return false;
    }

    public UserInfo findUser(String name){

        String sql = "select * from user_info where name = ?";

        Connection  con = JDBCUtils.getConn();
        UserInfo user = null;
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                int id = rs.getInt(1);
                String namedb = rs.getString(2);
                String pwddb  = rs.getString(3);
                String phone = rs.getString(4);
                int status = rs.getInt(5);
                user = new UserInfo(id,namedb,pwddb,phone,status);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return user;
    }


}

