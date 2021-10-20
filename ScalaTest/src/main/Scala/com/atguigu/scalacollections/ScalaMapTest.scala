package com.atguigu.scalacollections

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaMapTest {
  def main(args : Array[String]) : Unit = {
    //immutable
    val map1 : Map[String, Int] = Map[String, Int]( "a" -> 1, "b" -> 2 )
    println ( map1.getClass.getName )

    println ( map1.mkString ( "," ) )

    //mutable



  }

}
