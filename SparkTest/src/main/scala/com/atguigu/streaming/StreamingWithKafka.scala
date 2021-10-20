package com.atguigu.streaming

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies, PerPartitionConfig}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object StreamingWithKafka {
	def main(args : Array[String]) : Unit = {
		val sparkConf : SparkConf = new SparkConf ().setAppName ( "Kafka" ).setMaster ( "local[*]" )
//		/Users/lucienoz/Workspaces/learn/MavenTest/SparkTest/data/sparkStreamingCheckpoint
val ssc : StreamingContext = StreamingContext.getOrCreate ( "./SparkTest/data/sparkStreamingCheckpoint",
	() => {
		val streamingContext = new StreamingContext ( sparkConf, Seconds ( 1 ) )
		streamingContext.checkpoint("./SparkTest/data/sparkStreamingCheckpoint")


		/**
		 * Scala constructor for a DStream where
		 * each given Kafka topic/partition corresponds to an RDD partition.
		 * @param locationStrategy In most cases, pass in [[LocationStrategies.PreferConsistent]],
		 *   see [[LocationStrategies]] for more details.
		 * @param consumerStrategy In most cases, pass in [[ConsumerStrategies.Subscribe]],
		 *   see [[ConsumerStrategies]] for more details.
		 * @param perPartitionConfig configuration of settings such as max rate on a per-partition basis.
		 *   see [[PerPartitionConfig]] for more details.
		 * @tparam K type of Kafka message key
		 * @tparam V type of Kafka message value
		 */
		val kafkaParams : mutable.Map[String, String] = new mutable.HashMap[String,String]()

		kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop130.local:9092,hadoop131.local:9092")
		kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG,"IdeaTest")
		kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")
		kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")


		val kafkaDS : InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String]( streamingContext,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String]( List ( "bigdata20210701" ), kafkaParams )
		)
		val wordToOne : DStream[(String, Int)] = kafkaDS.flatMap ( _.value ().split ( " " ) ).map ( (_, 1) )
		//使用updateStateByKey计算历史上的WordCount
		/*wordToOne
			.updateStateByKey ( (seq, op : Option[Int]) => {
			val sum : Int = seq.sum
			val old : Int = op.getOrElse ( 0 )
			Some ( old + sum )
		} ).print()*/


		//使用窗口飘逸操作时间阶段window
		wordToOne.window(Seconds(6),Seconds(2)).reduceByKey(_+_).foreachRDD(rdd=>{
			rdd.foreach{case (word,nums)=>{
				println(s"$word -> $nums")
			}}
		})

		println("-"*30)
		//使用窗口飘逸操作时间阶段reduceByKeyAndWindow
		//such that for all y, invertible x: invReduceFunc(reduceFunc(x, y), x) = y

		wordToOne.reduceByKeyAndWindow(_+_,(now,prev)=>now-prev,Seconds(10)).print()





		streamingContext
	} )



		new Thread(new StopStreamingContext(ssc)).start()


		ssc.start()
		ssc.awaitTermination()

		println("StreamingContext is closed")

	}


}
