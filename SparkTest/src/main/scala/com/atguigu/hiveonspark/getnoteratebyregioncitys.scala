package com.atguigu.hiveonspark

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator

import scala.collection.mutable


/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
case class getnoteratebyregioncitys(var keepNums:Int) extends Aggregator[String,CitiesBuff,String]{

	override def zero : CitiesBuff = CitiesBuff(0L,mutable.Map[String,Long]())

	override def reduce(b : CitiesBuff, a : String) : CitiesBuff = {
		b.totalClick += 1L
		val oldCnt : Long = b.citiesMap.getOrElse ( a, 0 )
		b.citiesMap.put(a,oldCnt+1L)
		b
	}

	override def merge(b1 : CitiesBuff, b2 : CitiesBuff) : CitiesBuff = {
		b1.totalClick+=b2.totalClick
		for ((city,cnt) <- b2.citiesMap) {
			val oldCnt : Long = b1.citiesMap.getOrElse ( city, 0 )
			b1.citiesMap.put(city,oldCnt+cnt)
		}
		b1
	}

	override def finish(reduction : CitiesBuff) : String = {
		val listTopNums : List[(Double, String)] = reduction.citiesMap.toList.sortBy ( _._2 )( Ordering.Long.reverse )
			.take ( keepNums ).map ( tuple => (tuple._2 * 100.0 / reduction.totalClick, tuple._1 + "\t" + (tuple._2 * 100.0 / reduction.totalClick).formatted("%.2f") + "%" ) )
		val totalClickRate : Double = listTopNums.map(_._1).reduce(_+_)

		listTopNums.map(_._2).mkString(",")+"\tothers\t"+(100-totalClickRate).formatted("%.2f")+"%"

	}

	override def bufferEncoder : Encoder[CitiesBuff] = Encoders.product

	override def outputEncoder : Encoder[String] = Encoders.STRING
}
