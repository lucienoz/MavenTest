package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class myParitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(value.toString().contains("hello")) return 0;
        else if(value.toString().contains("123")) return 1;
        else return 2;
    }

    @Override
    public void close() {
        System.out.println("yoyoyo");
    }


    @Override
    public void configure(Map<String, ?> configs) {
    }
}
