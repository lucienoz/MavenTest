package com.atguigu.mr.PhoneNumPartition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 * Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 */
public class PhoneFlowInfoReducer extends Reducer<Text, PhoneFlowInfoBean,Text, PhoneFlowInfoBean> {
    @Override
    protected void reduce(Text key, Iterable<PhoneFlowInfoBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (PhoneFlowInfoBean value : values) {
             sumUpFlow +=value.getUpFlow();
             sumDownFlow +=value.getDownFlow();
        }
        PhoneFlowInfoBean phoneFlowInfoBean = new PhoneFlowInfoBean();
        phoneFlowInfoBean.setUpFlow(sumUpFlow);
        phoneFlowInfoBean.setDownFlow(sumDownFlow);
        phoneFlowInfoBean.setSumFlow();
        context.write(key, phoneFlowInfoBean);

    }
}
