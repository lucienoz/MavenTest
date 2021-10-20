package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/10.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ReduceByKeyTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "sample" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[(String, Int)] = sc.makeRDD ( List ( ("a",1), ("b",2), ("a",3), ("d",4), ("a",5) ), 2 )
    val reduceRDD : RDD[(String, Int)] = listRDD.reduceByKey ( _ + _, 3 )
    println ( reduceRDD.collect ().mkString ( "," ) )

//    partitionsRDD.saveAsTextFile("SparkTest/data/output")

    sc.stop()
  }

}
