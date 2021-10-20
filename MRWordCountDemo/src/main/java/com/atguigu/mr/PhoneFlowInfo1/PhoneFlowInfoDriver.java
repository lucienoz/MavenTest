package com.atguigu.mr.PhoneFlowInfo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class PhoneFlowInfoDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //声明Job对象(MR程序抽象对象)
        Configuration configuration = new Configuration();

        //设置HDFS NameNode的地址
//        configuration.set("fs.defaultFS", "hdfs://hadoop130.local:9820");
//        // 指定MapReduce运行在Yarn上
//        configuration.set("mapreduce.framework.name", "yarn");
//        // 指定mapreduce可以在远程集群运行
//        configuration.set("mapreduce.app-submission.cross-platform", "true");
//        //指定Yarn resourcemanager的位置
//        configuration.set("yarn.resourcemanager.hostname", "hadoop131.local");
//
//        //设置datanode节点返回hostname
//        configuration.set("dfs.client.use.datanode.hostname", "true");


        Job job = Job.getInstance(configuration);

        //指定当前执行驱动类
        job.setJarByClass(PhoneFlowInfoDriver.class);
//        job.setJar("/Users/lucienoz/Workspaces/learn/MavenTest/HDFSWordCountDemo/target/HDFSWordCountDemo-1.0-SNAPSHOT.jar");


        //指定当前JOB Mapper
        job.setMapperClass(PhoneFlowInfoMapper.class);
        //指定当前JOB Reducer
        job.setReducerClass(PhoneFlowInfoReducer.class);
        //指定当前Map阶段输出的key和value的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneFlowInfoBean.class);
        //指定Reducer输出的 key 和 value 数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneFlowInfoBean.class);

        //指定输入数据路径
        FileInputFormat.setInputPaths(job, args[0]);
        //指定输出数据路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //指定Job完成监控

        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }
}
