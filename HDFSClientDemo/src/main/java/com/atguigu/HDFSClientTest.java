package com.atguigu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/17.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class HDFSClientTest {

    /**
     * 文件从本地拷贝到HDFS
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void copyFromLocal() throws URISyntaxException, IOException, InterruptedException {
        //创建Hadoop Configuration对象
        Configuration configuration = new Configuration();
        //设置配置Hadoop 集群返回连接字符串使用Datanode的hostname，而不是IP地址，防止外网ip无法连接到内网集群
        configuration.set("dfs.client.use.datanode.hostname", "true");
        //通过hdfs连接字符串得到HDFS的连接实例FileSystem
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130.local:9820"),//连接字符串
                configuration, //HDFS临时配置对象
                "raylu"); //连接用户

        fs.copyFromLocalFile(new Path("/Users/lucienoz/Desktop/" +
                        "04.尚硅谷大数据技术之Hadoop/02.资料/02_linux环境编译源码/" +
                        "apache-ant-1.10.7-bin.tar.gz"),
                new Path("/Users/lucienoz/Desktop/" +
                        "04.尚硅谷大数据技术之Hadoop/02.资料/" +
                        "02_linux环境编译源码/c//"));
        fs.close();
        System.out.println("文件上传完成");
    }

    /**
     * 在HDFS上移动文件
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void mv() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130.local:9820"),
                configuration,
                "raylu");
        fs.delete(new Path("/tests.tar"),true);
        fs.rename(new Path("/demo.pdman.json"), new Path("/tests.tar"));
        fs.close();
        System.out.println("文件上传完成");
    }


    /**
     * 在HDFS上删除文件
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void delFile() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130.local:9820"),
                configuration,
                "raylu");

        fs.delete(new Path("/download.php"),true);
        fs.close();
        System.out.println("文件上传完成");
    }

    /**
     * 查询HDFS上文件夹或文件的属性
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void getPropFile() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130.local:9820"),
                configuration,
                "raylu");

        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);

        while (files.hasNext()) {
            LocatedFileStatus next = files.next();
            System.out.println(next.getGroup() + "\t" + next.getPath() + "\t" +
                    next.getReplication() + "\t" +
                    next.getOwner() + "\t" +
                    next.getPath().getName()+"\t");
            //getPath()
//            next.getPath().getName();//文件名
//            next.getPath().getParent();//文件父文件夹
//            next.getPath().toUri(); //文件地址的URI对象
            //files.next().setSymlink(new Path(files.next().getPath().getParent()+files.next().getPath().getName()+"-link"));
        }

//        Configuration conf = fs.getConf();

        fs.close();
        System.out.println("文件上传完成");
    }

}
