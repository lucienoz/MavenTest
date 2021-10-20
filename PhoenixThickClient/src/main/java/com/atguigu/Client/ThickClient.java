package com.atguigu.Client;

import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/13.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class ThickClient {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:phoenix:hadoop130.local,hadoop131.local,hadoop132.local:2181";
        Properties props = new Properties();
        props.put("phoenix.schema.isNamespaceMappingEnabled", "true");
        Connection connection = DriverManager.getConnection(url, props);
        String sql = "select * from mydb.emp";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("--------------------------");
            System.out.println(resultSet.getString("EMPNO"));
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getInt("AGE"));
            System.out.println(resultSet.getString("GANDER")=="f"?"female":"male");

        }


    }
}
