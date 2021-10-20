package com.atguigu.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.apache.hadoop.hbase.Version.url;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/13.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class ClientAPI {
    private static Connection connection;
    static {
        String url = "jdbc:phoenix:hadoop130.local,hadoop131.local,hadoop132.local:2181";
        Properties properties = new Properties();
        properties.put("phoenix.schema.isNamespaceMappingEnabled", "true");
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }


}
