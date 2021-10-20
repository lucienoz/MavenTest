package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/8.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class MyKafkaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        ArrayList<String> topics = new ArrayList<>();
        topics.add("first");
        kafkaConsumer.subscribe(topics);
        ConsumerRecords<String, String> records = null;
        while (true) {
            records = kafkaConsumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {
                System.out.print("record.timestamp() = " + record.timestamp()+"\n");
                System.out.print("record.topic() = " + record.topic()+"\t");
                System.out.print("record.offset() = " + record.offset()+"\t");
                System.out.print("record.headers() = " + record.headers()+"\t");
                System.out.print("record.value() = " + record.value()+"\t");
                System.out.print("record.partition() = " + record.partition()+"");
            }
        }


    }
}
