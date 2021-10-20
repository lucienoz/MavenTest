package com.atguigu.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/10.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object FilterTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "filter" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )
    val intsRDD : RDD[Int] = listRDD.filter ( _ % 2 == 0 )

    intsRDD.saveAsTextFile("SparkTest/data/output")

    intsRDD.collect().foreach(println)


  }

}
