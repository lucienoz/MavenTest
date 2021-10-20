package com.atguigu.mr.PhoneFlowInfo;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class PhoneFlowInfoBean implements Writable {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void setSumFlow() {
        this.sumFlow = this.upFlow + this.downFlow;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        //upFlow java基础数据类型
        dataOutput.writeLong(upFlow);
        //downFlow java基础数据类型
        dataOutput.writeLong(downFlow);
        //sumFlow java基础数据类型
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        //通过正确的方法拿到正确数据类型的值否则会出现问题
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return "PhoneFlowInfoBean{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", sumFlow=" + sumFlow +
                '}';
    }
}
