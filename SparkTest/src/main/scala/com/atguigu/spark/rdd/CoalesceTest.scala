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
object CoalesceTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "sample" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )
    val listRDD2 : RDD[Int] = listRDD.coalesce ( 5,false )

    listRDD2.saveAsTextFile("SparkTest/data/output")

    sc.stop()
  }

}
