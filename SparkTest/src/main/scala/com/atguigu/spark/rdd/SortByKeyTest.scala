package com.atguigu.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/12.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object SortByKeyTest {
  def main(args : Array[String]) : Unit = {
    sorkByTest
  }

  def sorkByTest={
    val sparkConf : SparkConf = new SparkConf ().setMaster ( "local[*]" ).setAppName ( "sortBy" )
    val sc : SparkContext = new SparkContext ( sparkConf )
    val usersRDD : RDD[User] = sc.makeRDD ( List ( User ( 12 ), User ( 11 ), User ( 10 ), User ( 32 ) ) )
    usersRDD.sortBy(user=>user).collect().foreach(println)
    sc.stop()
  }


}
case class User( age:Int) extends Ordered[User]{
  override def toString : String = "age="+age

  override def compare(that : User) : Int = this.age - that.age
}

case class User2( age:Int){
}
