package com.atguigu.mr.WordCount0;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private IntWritable outValue = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum+= value.get();
        }
        outValue.set(sum);
        context.write(key, outValue);
    }
}
