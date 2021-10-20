package com.atguigu.mr.WordCount1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 * Mapper<KEYIN, //当前行在文件中的偏移量
 *        VALUEIN, //当前行内容
 *        KEYOUT,  //行中一个单词
 *        VALUEOUT> //单词标记
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private Text outKey = new Text();
    private IntWritable outValue = new IntWritable(1);
    /**
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取当前行数据
        String line = value.toString();
        //根据分割符分割成单词集合
        String[] words = line.split(" ");
        //循环遍历 单词集合 组合 <K,V> 进行输出
        for (String word : words) {
            outKey.set(word);
            context.write(outKey, outValue);
        }
    }
}
