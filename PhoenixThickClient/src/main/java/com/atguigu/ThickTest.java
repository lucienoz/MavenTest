package com.atguigu;

import com.atguigu.exercise.ClientAPI;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/13.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class ThickTest {

    @Test
    public void delete() throws SQLException {
        Connection connection = ClientAPI.getConnection();
//        connection.setAutoCommit(true);
        System.out.println("connection.getAutoCommit() = " + connection.getAutoCommit());
        PreparedStatement preparedStatement = connection.prepareStatement("delete from mydb.emp where empno = '1006'");
//        preparedStatement.setString(1, "1006");

        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("delete success!");
        }
//        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void upsert() throws SQLException {
        Connection conn = ClientAPI.getConnection();
        String sql = "select * from mydb.emp where empno = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "1006");
        ResultSet resultSet = preparedStatement.executeQuery();
        String empno = null;
        String name = null;
        int age = 0;
        String gender = null;
        if (resultSet.next()) {
            name = resultSet.getString("name");
            empno = resultSet.getString("empno");
            age = resultSet.getInt("age");
            gender = resultSet.getString("gander");
            name = name + ":" + name;
        }
        resultSet.close();
        preparedStatement.close();

        sql = "upsert into mydb.emp values (?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,empno);
        pstmt.setString(2, name);
        pstmt.setInt(3, age);
        pstmt.setString(4, gender);

        int i = pstmt.executeUpdate();

        if(i>0){
            System.out.println("upsert success!");
        }
        conn.commit();
        preparedStatement.close();
        pstmt.close();
        conn.close();

    }

}
