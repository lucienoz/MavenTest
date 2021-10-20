package com.atguigu.mr.PhoneFlowInfo1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 */
public class PhoneFlowInfoMapper extends Mapper<LongWritable,Text,Text, PhoneFlowInfoBean> {
    private PhoneFlowInfoBean phoneFlowInfoBean = new PhoneFlowInfoBean();
    private Text phoneNum = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strs = value.toString().split("\t");
        phoneFlowInfoBean.setUpFlow(Long.parseLong(strs[strs.length-3]));
        phoneFlowInfoBean.setDownFlow(Long.parseLong(strs[strs.length-2]));
        phoneFlowInfoBean.setSumFlow();
        phoneNum.set(strs[1]);
        context.write(phoneNum,phoneFlowInfoBean);
    }
}
