package com.atguigu.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/12.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object CombineByKeyTest {
  def main(args : Array[String]) : Unit = {

    Test
    println("==================")
    combineByKeyTest

  }

  def Test={
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "aggregateByKey" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val charListRDD : RDD[(String, Int)] = sc.makeRDD ( List ( ("a",92),("a",93),("b",12),("b",99),("b",32) ) )
    charListRDD.aggregateByKey((0,0))((z,u)=>(z._1+u,z._2+1),(left,right)=>(left._1+right._1,left._2+right._2)).mapValues(tuple=>tuple._1/tuple._2).collect().foreach(println)
    sc.stop()
  }

  def combineByKeyTest={
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "aggregateByKey" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val charListRDD : RDD[(String, Int)] = sc.makeRDD ( List ( ("a",92),("a",93),("b",12),("b",99),("b",32) ) )
    charListRDD.combineByKey((_,1),(tuple:(Int,Int),int)=>(tuple._1+int,tuple._2+1),(left:(Int,Int),right:(Int,Int))=>(left._1+right._1,left._2+right._2)).mapValues(tuple=>tuple._1 / tuple._2).collect().foreach(println)
    sc.stop()
  }

}
