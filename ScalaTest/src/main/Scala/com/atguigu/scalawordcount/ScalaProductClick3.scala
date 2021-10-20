package com.atguigu.scalawordcount

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaProductClick3 {
  def main(args : Array[String]) : Unit = {
    println ( scala.io.Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/data.txt", "UTF-8" ).
      getLines ().toList.map({
      str:String =>str.trim.replace ( ",", "" )
        .replace ( "(", "" )
        .replace ( ")", "" )
        .replace("\"","").split(" ").toList
    }).groupBy{
      case List(name,province,product)=>province
    }.map{
      case (province,list )=>(province,list.groupBy{
        case List(name,province,product)=>product
      }.map{
        case (product,proList)=>(product,proList.size)
      })
    }
    )
  }
}
