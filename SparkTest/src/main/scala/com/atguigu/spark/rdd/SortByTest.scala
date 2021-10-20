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
object SortByTest {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "sample" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    val listRDD : RDD[Int] = sc.makeRDD ( List ( 5, 4, 3, 6, 2, 1 ), 3 )
    val listRDD2 : RDD[Int] = sc.makeRDD ( List ( 6, 7, 8, 9 ), 3 )
    val listRDD3 : RDD[String] = sc.makeRDD ( List ( "a", "b", "c", "d" ), 3 )

    val ascRDD : RDD[Int] = listRDD.sortBy ( num => num, true )

    val interRDD : RDD[Int] = listRDD.intersection ( listRDD2 )
    println ( interRDD.collect ().mkString ( "," ) )
    //    ascRDD.collect().foreach(println )
    val unionRDD : RDD[Int] = listRDD.union ( listRDD2 )
    println ( unionRDD.collect ().mkString ( "," ) )

    val subtractRDD : RDD[Int] = listRDD.subtract ( listRDD2 )
    println ( subtractRDD.collect ().mkString ( "," ) )

    println ( listRDD2.zip ( listRDD3 ).collect ().mkString ( "," ) )

    sc.stop ()
  }

}
