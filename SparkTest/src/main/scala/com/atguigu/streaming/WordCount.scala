package com.atguigu.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object WordCount {
	def main(args : Array[String]) : Unit = {
		val streamingContext = new StreamingContext ( new SparkConf ().setAppName ( "WordCount" ).setMaster ( "local[*]" ),
			batchDuration = Seconds ( 1 ) )

		val receDS : ReceiverInputDStream[String] = streamingContext.socketTextStream ( "hadoop130.local", 9999 )

		receDS.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

		streamingContext.start()


		streamingContext.awaitTermination()
	}



}
