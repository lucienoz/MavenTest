package com.atguigu.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/12.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object FoldByKeyTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "aggregateByKey" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val charListRDD : RDD[String] = sc.makeRDD ( List ( "a", "b", "c", "d","a" ) )
    val intListRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4,5 ) )
    val hmapRDD : RDD[(String, Int)] = charListRDD.zip ( intListRDD )
    hmapRDD.foldByKey(0)(_+_).collect().foreach(println)
  }

}
