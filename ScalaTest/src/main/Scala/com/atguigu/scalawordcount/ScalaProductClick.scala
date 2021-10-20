package com.atguigu.scalawordcount

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */

/**
 * 不同省份商品点击排行
 */
object ScalaProductClick {
  def main(args : Array[String]) : Unit = {
    val clicks : List[String] = scala.io.Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/data.txt", "UTF-8" ).getLines ().toList
    println(clicks)
    val clicks2 : List[List[String]] = clicks.map( _.trim.replace ( ",", "" )
      .replace ( "(", "" )
      .replace ( ")", "" )
      .replace("\"","").split(" ").toList
    )
    println("------------------------")
    println ( clicks2 )

    val value : Map[(String, String), List[List[String]]] = clicks2.groupBy ( list => (list ( 1 ), list ( 2 )) )

    println("-----value-------------------")
    println(value)

    val value1 : Map[(String, String), Int] = value.map ( tuple => (tuple._1, tuple._2.size) )
    println("-----value1------------------")
    println(value1)




  }

}
