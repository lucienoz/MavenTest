package com.atguigu.scalawordcount

import scala.collection.MapView

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/5.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaProductClick2 {

  def main(args : Array[String]) : Unit = {
    val clicks : List[String] = scala.io.Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/data.txt", "UTF-8" ).getLines ().toList
    println(clicks)
    val clicks2 : List[List[String]] = clicks.map( _.trim.replace ( ",", "" )
      .replace ( "(", "" )
      .replace ( ")", "" )
      .replace("\"","").split(" ").toList
    )
    println("-------------clicks2-----------")
    println ( clicks2 )
    val clicks3 : Map[String, List[List[String]]] = clicks2.groupBy ( _ ( 1 ) )

    println("-----------clicks3-------------")
    println(clicks3)

    val clicks4 : Map[String, Map[String, List[List[String]]]] = clicks3.map ( tuple => (tuple._1, tuple._2.groupBy(_(2))))
    println("-----------clicks4-------------")
    println(clicks4)

    val clicks5  = clicks4.map ( tuple => (tuple._1, (tuple._2.map ( tupleTmp => (tupleTmp._1, tupleTmp._2.size) ).toList)) ).toList
    println("-----------clicks5-------------")
    println ( clicks5 )

    val value = clicks5.toMap.mapValues(_.sortBy(_._2)(Ordering.Int.reverse))
    println("-----------value-------------")
    println(value.mkString(","))




  }

}
