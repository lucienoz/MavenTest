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
object SampleTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "sample" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )


    val chooseIntsRDD : RDD[Int] = listRDD.sample ( false, 0.5 )

//    chooseIntsRDD.saveAsTextFile("SparkTest/data/output")

//    chooseIntsRDD.collect().foreach(println)
 println("=======================")
    val choose2IntsRDD : RDD[Int] = listRDD.sample ( true, 100 )
    choose2IntsRDD.collect ().foreach(println)



    sc.stop()


  }

}
