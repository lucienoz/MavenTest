package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.math.Ordering.IntOrdering

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/10.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object GlomTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "glom" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val listRDD : RDD[Int] = sc.makeRDD ( List ( 1, 2, 3, 4, 5 ), 3 )
    val listArrRDD : RDD[Array[Int]] = listRDD.glom ()

//    listArrRDD.saveAsTextFile("SparkTest/data/output")

    val intsRDD : RDD[Int] = listArrRDD.map ( _.max )
    val d : Double = intsRDD.sum ()
    println ( d )
    //intsRDD.collect().foreach(println)


  }

}
