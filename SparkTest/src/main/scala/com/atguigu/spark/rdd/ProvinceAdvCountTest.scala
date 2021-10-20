package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/12.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ProvinceAdvCountTest {
  def main(args : Array[String]) : Unit = {
    //时间戳，       省份，城市，用户，广告，中间字段使用空格分隔。
    //    1516609143867 6    7    64   16
    provinceAdvCountTest
  }

  def provinceAdvCountTest = {
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "ProvinceAdvCountTest" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val textRDD : RDD[String] = sc.textFile ( "SparkTest/data/agent.log" )
    textRDD.map ( line => {
      val strings : Array[String] = line.split ( " " )
      ((strings ( 1 ), strings ( strings.length - 1 )),1)
    }
    ).reduceByKey(_+_) //((province,adv),cnt)
      .map{ case ((province,adv),cnt)=>(province,(adv,cnt))}//(province,(adv,cnt))
      .groupByKey() //(province,cb((adv1,cnt),(adv2,cnt)...))
      .mapValues(_.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)).sortByKey().collect().foreach(println)
    while(true){
      Thread.sleep(10000)
    }
    sc.stop()


  }

}
