package com.atguigu.scala

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/11.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaTest {
  def main(args : Array[String]) : Unit = {
    val ints : Array[Int] = Array[Int]( 1, 2, 3, 4, 5, 6 )
    val ints1 : ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val unit : Unit = ints1.appendAll ( ints )
    println ( ints1.mkString ( "," ) )

    println ( func ( (a,b)=> {
      val ints2: List[Int] = a::b.toList
      val sum : Int = ints2.sum
      sum
    }) )

    println ( func1 ( 1, 2, 3, 4 ) )
    println ( func1 ( 1, 2, 3, 4 ) )

  }

  def func(f:(Int,List[Int])=>Int):Int={
    f(1,List(1,2,3,4))
  }
  def func1(a:Int,b:Int*):Int={
    val ints: List[Int] = a::b.toList
    ints.sum
  }





}
