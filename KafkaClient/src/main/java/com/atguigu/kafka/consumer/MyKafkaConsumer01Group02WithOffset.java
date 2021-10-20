package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/8.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class MyKafkaConsumer01Group02WithOffset {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group02");

        //设置consumer拉取数据的分区分配策略
//        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");//RoundRobin
//        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RangeAssignor");//Range
//        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.StickyAssignor");//粘性分配策略

//       重置offset读取
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"none");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        ArrayList<String> topics = new ArrayList<>();
        topics.add("second");
        kafkaConsumer.subscribe(topics);
        ConsumerRecords<String, String> records = null;

        for (int i=0;i<=5;i++) {
            records = kafkaConsumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {
                System.out.print("timestamp() = " + record.timestamp()+"\t");
                System.out.print("topic() = " + record.topic()+"\t");
                System.out.print("offset() = " + record.offset()+"\t");
                System.out.print("headers() = " + record.headers()+"\t");
                System.out.print("value() = " + record.value()+"\t");
                System.out.println("partition() = " + record.partition()+"");
            }
        }


    }
}
