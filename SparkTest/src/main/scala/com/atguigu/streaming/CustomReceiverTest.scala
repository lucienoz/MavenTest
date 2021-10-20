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
object CustomReceiverTest {
	def main(args : Array[String]) : Unit = {
		val streamingContext = new StreamingContext ( new SparkConf ().setAppName ( "WordCount" ).setMaster ( "local[*]" ),
			batchDuration = Seconds ( 1 ) )

		val RDS : ReceiverInputDStream[String] = streamingContext.receiverStream ( new CustomReceiver )
		RDS.print()

		streamingContext.start()


		streamingContext.awaitTermination()
	}



}
