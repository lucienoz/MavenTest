package com.atguigu.kafka.producer02;

import org.apache.kafka.clients.producer.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class KafkaClientWithCallbackAndPartitioner {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ExecutionException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,"128");
        props.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, myParitioner.class);

        //设置At least Once + 幂等性 = Exactl Once
        props.put(ProducerConfig.ACKS_CONFIG,"-1"); //At least Once 至少一次
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true); //y=1^x 幂等性



        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        for (int i = 0; i < 500; i++) {
            Thread.sleep(100);
            kafkaProducer.send(new ProducerRecord<>("second",""+i, "12abc  " + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if(exception == null) System.out.printf("topic = %s\ttimestamp = %s\tpartition=%s\toffset=%s\n",metadata.topic(),
                                    metadata.timestamp(),metadata.partition(),metadata.offset());
                            else System.out.println("数据传送失败");
                        }
                    }).get();
        }
        kafkaProducer.close();
    }
}
