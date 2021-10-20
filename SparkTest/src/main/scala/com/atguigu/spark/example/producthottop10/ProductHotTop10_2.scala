package com.atguigu.spark.example.producthottop10

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/13.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ProductHotCategoryTop10_2 {
  def main(args : Array[String]) : Unit = {
    val sparkConf : SparkConf = new SparkConf ().setAppName ( "ProductHotCategoryTop10" ).setMaster ( "local[*]" )
    val sc : SparkContext = new SparkContext ( sparkConf )

    //热门品类Top10
    //点击数 -> 下单数 -> 支付数 ->
    //Step1. 读数据
    val logfileRDD : RDD[String] = sc.textFile ( "SparkTest/data/user_visit_action.txt" )

    //品类，点击数
    val cateByClicks : RDD[(String, Int)] = logfileRDD.filter (
      line => line.split ( "_" )( 6 ) != "-1"
    ).map (
      line => {
        val datas : Array[String] = line.split ( "_" )
        (datas ( 6 ), 1)
      }
    ).reduceByKey ( _ + _ )




    //品类，下单数
    val cateByOrders : RDD[(String, Int)] = logfileRDD.filter (
      line => line.split ( "_" )( 8 ) != "null"
    ).flatMap (
      line => {
        val datas : Array[String] = line.split ( "_" )
        val categorys : Array[String] = datas ( 8 ).split ( "," )
        categorys.map ( (_, 1) )
      }
    ).reduceByKey ( _ + _ )

    //品类，支付数

    val cateByPays : RDD[(String, Int)] = logfileRDD.filter (
      line => line.split ( "_" )( 10 ) != "null"
    ).flatMap (
      line => {
        val datas : Array[String] = line.split ( "_" )
        val categorys : Array[String] = datas ( 10 ).split ( "," )
        categorys.map ( (_, 1) )
      }
    ).reduceByKey ( _ + _ )

    val cateRDD : RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = cateByClicks.cogroup ( cateByOrders, cateByPays )
    val cateRDD2 : RDD[(String, (Int, Int, Int))] = cateRDD.mapValues {
      case (iter01, iter02, iter03) =>
        (iter01.sum, iter02.sum, iter03.sum)
    }

    val tuples : Array[(String, (Int, Int, Int))] = cateRDD2.sortBy ( _._2, false ).take ( 10 )
    tuples.foreach(println)


  }

}
