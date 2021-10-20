package com.atguigu.scalawordcount

import java.io.{BufferedReader, BufferedWriter, FileOutputStream, OutputStreamWriter, PrintWriter}
import java.nio.charset.StandardCharsets
import scala.collection.MapView
import scala.io.{BufferedSource, Source}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaWordCountTest {
  def main(args : Array[String]) : Unit = {
    val source : BufferedSource = Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/wordcount.txt", "UTF-8" )
    val list : List[String] = source.getLines ().toList
    println ( list.mkString ( "," ) )

    println ( list.flatMap ( line => line.split ( " " ) ).mkString ( "," ) )
    println ( list.flatMap ( line => line.split ( " " ) ).groupBy ( line => line ).map ( t => (t._1, t._2.size) ).mkString ( "," ) )
    println ( list.flatMap ( line => line.split ( " " ) ).groupBy ( word => word ).mapValues ( _.size ).toList.sortBy ( t => t._2 ).mkString ( "," ) )

    val value : List[(String, Int)] = list.flatMap ( line => line.split ( " " ) ).groupBy ( word => word ).mapValues ( _.size ).toList.sortBy ( t => t._2 )

//    val fileOutputStream = new FileOutputStream ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/output/wordcount_out.txt", false )
//
//    val outputStreamWriter = new OutputStreamWriter ( fileOutputStream, StandardCharsets.UTF_8 )
//    val writer = new BufferedWriter ( outputStreamWriter )

    val printWriter = new PrintWriter ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/output/wordcount_out.txt", "UTF-8" )

    value.foreach(t => {
      printWriter.println(t._1+"\t"+t._2)
    })

    printWriter.flush()
    printWriter.close()
    source.close()




  }

}
