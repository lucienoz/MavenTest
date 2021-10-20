package com.atguigu.streaming

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

import java.util.UUID

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/19.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
class CustomReceiver extends Receiver[String](storageLevel = StorageLevel.MEMORY_ONLY_2){
	override def onStart() : Unit = {
		while(true){
			Thread.sleep(1000)
			val uuid : String = UUID.randomUUID ().toString
			store(uuid)
		}
	}

	override def onStop() : Unit = {

	}
}
