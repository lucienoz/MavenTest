package com.atguigu.guli;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/3.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class GuliMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text videoText = ETLUtils.getVideoText(value);
        NullWritable.get();
        if (videoText!=null) {
            context.write(videoText,NullWritable.get());
        }


    }
}
