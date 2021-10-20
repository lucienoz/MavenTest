package com.atguigu.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/9.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object SparkWordCountTest {
  def main(args : Array[String]) : Unit = {
    val wordCount : SparkConf = new SparkConf ().setAppName ( "appWordCount" ).setMaster ( "local[*]" )
    val sc = new SparkContext ( wordCount )
    val textFile : RDD[String] = sc.textFile ( "SparkTest/data/wordcount.txt" )
    val words : RDD[String] = textFile.flatMap ( _.split(" ") )
    val wordsFormat : RDD[(String, Int)] = words.map ( (_, 1) )
    val wordsCount : RDD[(String, Int)] = wordsFormat.reduceByKey ( _ + _ )
    wordsCount.collect().foreach(println)
    sc.stop()

  }

}
