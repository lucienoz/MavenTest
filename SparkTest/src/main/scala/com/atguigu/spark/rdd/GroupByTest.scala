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
object GroupByTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "mapPartition" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )
    val groupRDD : RDD[(Int, Iterable[Int])] = listRDD.groupBy ( _ % 2 ,2)
    groupRDD.saveAsTextFile("SparkTest/data/output")
    val array : Array[(Int, Iterable[Int])] = groupRDD.collect ()
    println ( array.mkString ( "," ) )
    sc.stop()

  }

}
