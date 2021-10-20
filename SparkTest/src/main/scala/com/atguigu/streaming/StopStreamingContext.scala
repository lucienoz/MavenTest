package com.atguigu.streaming

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.streaming.{StreamingContext, StreamingContextState}

import java.net.URI

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
class StopStreamingContext(val ssc : StreamingContext) extends Runnable {

	private val fileSystem : FileSystem = FileSystem.get ( new URI ( "hdfs://hadoop130.local:8020" ), new Configuration (), "raylu" )


	override def run() : Unit = {
		while (true) {
			println("check")
			Thread.sleep ( 5000 )
			val isExists : Boolean = fileSystem.exists ( new Path ( "hdfs://hadoop130.local:8020/sparkStreaming/stop" ) )
			if(isExists){
				if (ssc.getState () ==StreamingContextState.ACTIVE) {
					ssc.stop(true,true)
				}
				System.exit(0)
			}

		}


	}
}
