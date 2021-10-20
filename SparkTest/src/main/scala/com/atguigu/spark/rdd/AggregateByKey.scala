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
object AggregateByKey {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "aggregateByKey" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val charListRDD : RDD[String] = sc.makeRDD ( List ( "a", "b", "c", "d","a" ) )
    val intListRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4,5 ) )
    val hmapRDD : RDD[(String, Int)] = charListRDD.zip ( intListRDD )
    val aggregateRDD : RDD[(String, Int)] = hmapRDD.aggregateByKey ( 0 )( _ + _ , _ + _ )
    aggregateRDD.collect().foreach(println)
    println(Int.MaxValue+1)//2147483647
                         //2147483648
    sc.stop()
  }

}
