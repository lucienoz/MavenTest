package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/10.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object MapPartitionerTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "mapPartition" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )
    val mappartRDD : RDD[Int] = listRDD.mapPartitions{
      list =>
        val list1 : List[Int] = list.toList
        println ( list1.mkString ( "," ) )
        List ( list1.max ).iterator
    }
    mappartRDD.collect().foreach(println)

  }

}
