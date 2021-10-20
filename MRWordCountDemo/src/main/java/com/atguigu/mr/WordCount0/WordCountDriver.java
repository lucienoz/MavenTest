package com.atguigu.mr.WordCount0;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //声明Job对象(MR程序抽象对象)
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);
        //指定当前执行驱动类
        job.setJarByClass(WordCountDriver.class);

        //指定当前JOB Mapper
        job.setMapperClass(WordCountMapper.class);
        //指定当前JOB Reducer
        job.setReducerClass(WordCountReducer.class);
        //指定当前Map阶段输出的key和value的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定输出的 key 和 value 数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定输入数据路径
        FileInputFormat.setInputPaths(job, args[0]);
        //指定输出数据路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //指定Job完成监控

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);

    }
}
