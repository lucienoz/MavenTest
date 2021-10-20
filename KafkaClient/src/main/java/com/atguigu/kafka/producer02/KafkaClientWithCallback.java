package com.atguigu.kafka.producer02;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class KafkaClientWithCallback {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,"128");
        props.put(ProducerConfig.LINGER_MS_CONFIG,"1");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
//            Thread.sleep(100);
            kafkaProducer.send(new ProducerRecord<>("first", "abc","heiheihei " + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if(exception == null) System.out.printf("topic = %s\ttimestamp = %s\tpartition=%s\toffset=%s\n",metadata.topic(),
                                    metadata.timestamp(),metadata.partition(),metadata.offset());
                            else System.out.println("数据传送失败");
                        }
                    });
        }
        kafkaProducer.close();
    }
}
