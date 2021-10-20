package com.atguigu.Client;

import java.sql.*;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/13.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class PhoenixThinClient {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.put("phoenix.schema.isNamespaceMappingEnabled", "true");
        Connection connection = DriverManager.getConnection("jdbc:phoenix:hadoop130.local,hadoop131.local,hadoop132.local:2181",properties);

        String sql = "select * from mydb.emp";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("-------------------------------------------");
            System.out.println("resultSet.getString(1) = " + resultSet.getString(1));
            System.out.println("resultSet.getString(2) = " + resultSet.getString(2));
            System.out.println("resultSet.getString(3) = " + resultSet.getString(3));
            System.out.println("resultSet.getString(4) = " + resultSet.getString(4));
        }


    }
}
