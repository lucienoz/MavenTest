package com.atguigu.sparkstreamingexample

import org.apache.commons.lang.math.RandomUtils

import scala.collection.immutable

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/20.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object RandomList {

	/**
	 *
	 * @param scope 数组元素的范围 0-scope
	 * @param nums  数组的个数
	 * @return      Seq[Int]
	 */
	def getRandomList(scope : Int,nums:Int):Seq[Int]={
		val seq : immutable.IndexedSeq[Int] = for (num <- 1 to nums) yield {
			RandomUtils.nextInt ( scope )
		}
		seq
	}

	//User - Product - City 进行zip

}
