package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/9.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object RddTest {
  def main(args : Array[String]) : Unit = {
    //从内存中创建RDD
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "SparkRddTest" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )
//    val listRdd : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ) )
//    listRdd.collect().foreach(println)
    //从文件中创建RDD

    val textRDD : RDD[(String, String)] = sc.wholeTextFiles ( "SparkTest/data" )
    textRDD.saveAsTextFile("SparkTest/data/output")
    val wordRDD : RDD[String] = textRDD.flatMap ( _._2.replace("\n"," ").split ( " " ) )
    val wordCountRDD : RDD[(String, Int)] = wordRDD.map ( (_, 1) ).reduceByKey ( _ + _ )
    wordCountRDD.saveAsTextFile("SparkTest/data/output1")

    sc.stop()

  }

}
