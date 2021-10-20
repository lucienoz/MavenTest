package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.record.Record;

import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class KafkaClient {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<>("first","heiheihei "+ i));
        }
        kafkaProducer.close();
    }


}
