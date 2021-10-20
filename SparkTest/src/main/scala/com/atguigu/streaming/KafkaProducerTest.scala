package com.atguigu.streaming

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object KafkaProducerTest {
	def main(args : Array[String]) : Unit = {

		val prop : Properties = new Properties ()
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092")
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
		prop.put(ProducerConfig.BATCH_SIZE_CONFIG,"128")
		prop.put(ProducerConfig.LINGER_MS_CONFIG,"1")

		val producer : KafkaProducer[String, String] = new KafkaProducer[String, String]( prop )

		while(true){
			Thread.sleep(1000)
			producer.send(new ProducerRecord[String,String]("bigdata20210701","hello world hello scala hello spark"))
		}





	}

}
