package com.atguigu.hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.protobuf.generated.TableProtos;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/11.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class HBaseClientDemo {
    private static Connection connection;
    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop130.local,hadoop131.local,hadoop132.local");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        createNameSpace("mydb1");
        createTable("mydb1", "staff", "info1","info2");
    }

    //ddl

    /**
     * 创建NameSpace
     * @param nameSpaceName
     * @return
     * @throws IOException
     */
    public static void createNameSpace(String nameSpaceName) throws IOException {
        Admin admin = connection.getAdmin();
        NamespaceDescriptor mydb1 = NamespaceDescriptor.create(nameSpaceName).build();
        try {
            admin.createNamespace(mydb1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            admin.close();
        }
    }

    /**
     * 创建表
     * @param tableName
     * @param cfs
     * @return
     */
    public static void createTable(String nameSpace,String tableName,String ... cfs) throws IOException {
        //判断表明是否为空
        if("".equals(tableName)){
            System.out.println("表名为空");
            return ;
        }
        if (cfs.length == 0 || cfs == null) {
            System.out.println("需要指定至少一个列族");
        }

//        TableName tableName1 = TableName.valueOf(nameSpace+":"+tableName);
        TableName tableName1 = TableName.valueOf(nameSpace,tableName);
        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(tableName1).build();
        for (String cf : cfs) {
            ((TableDescriptorBuilder.ModifyableTableDescriptor) tableDescriptor).setColumnFamily(ColumnFamilyDescriptorBuilder.of(Bytes.toBytes(cf)));
        }
        Admin admin = connection.getAdmin();
        admin.createTable(tableDescriptor);
        admin.close();
        System.out.println("done");
    }




}
