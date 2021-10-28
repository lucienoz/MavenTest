package com.atguigu.sparkstreamingexample

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig}

import java.util.Properties

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/26.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object KafkaUtil {

	def getKafkaProducer : KafkaProducer[String, String] ={
		val prop : Properties = new Properties ()
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092")
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
		prop.put(ProducerConfig.BATCH_SIZE_CONFIG,"128")
		prop.put(ProducerConfig.LINGER_MS_CONFIG,"1")
		val producer : KafkaProducer[String, String] = new KafkaProducer[String, String]( prop )
		producer
	}

}
