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
object PartitionByTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "sample" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[(String, Int)] = sc.makeRDD ( List ( ("a",1), ("b",2), ("c",3), ("d",4), ("e",5) ), 2 )
    val partitionsRDD : RDD[(String, Int)] = listRDD.partitionBy ( new HashPartitioner ( 3 ) )


    partitionsRDD.saveAsTextFile("SparkTest/data/output")

    sc.stop()
  }

}
