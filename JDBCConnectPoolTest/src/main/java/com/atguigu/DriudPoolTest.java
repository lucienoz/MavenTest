package com.atguigu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/12.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class DriudPoolTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DruidDataSource druidDataSource = new DruidDataSource(); //阿里巴巴druid连接池

        //设置连接数据库参数
        //1）注册驱动
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //2）设置连接地址
        druidDataSource.setUrl("jdbc:mysql://192.168.1.106/testjdbc");
        //3)设置连接用户名
        druidDataSource.setUsername("mac");
        //4）设置连接密码
        druidDataSource.setPassword("root");


        //设置连接池参数
        //1）设置连接池初始化连接数
        druidDataSource.setInitialSize(5);

        //2）设置连接池最大连接池
        druidDataSource.setMaxActive(10);

        //3）设置等待连接最大时间
        druidDataSource.setMaxWait(1000);

        for (int i = 0; i < 15; i++) {
            Connection connection = druidDataSource.getConnection();
            System.out.println(i+"\tconnection = " + connection);
            System.out.println(druidDataSource.getActiveCount());//存活的连接数
            String sql = "select now() from dual;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Time time = resultSet.getTime(1);
                System.out.println(time);

            }



        }






    }


}
