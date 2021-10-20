package com.atguigu.scalaexercise.day01to02

import java.io._
import java.nio.charset.StandardCharsets
import scala.io.BufferedSource

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/23.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object Day01To02 {



  def main(args : Array[String]) : Unit = {

    //字符串拼接
//    StringTypeTest

    //输入输出
//    inTest
    //输出
    outTest

  }

  /**
   * 输出
   */
  def outTest : Unit = {
    //Java 方式一：
    val fileOutputStream = new FileOutputStream ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/output/output.txt" )
    val outputStreamWriter = new OutputStreamWriter ( fileOutputStream, StandardCharsets.UTF_8 );
    val bufferedWriter = new BufferedWriter ( outputStreamWriter )
    bufferedWriter.write("hello world")
    bufferedWriter.flush()
    bufferedWriter.close()

    //Java 方式二：
    val printWriter = new PrintWriter ( new OutputStreamWriter(new FileOutputStream("/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/output/output.txt" ,true),StandardCharsets.UTF_8))
    printWriter.println()
    printWriter.println("method 2")
    printWriter.flush()
    printWriter.close()


  }

  /**
   * 输入
   */
  def inTest : Unit = {
  //控制台输入
  val age : Int = scala.io.StdIn.readInt ()
  print ( "age=" + age )

  //从文件中读取 Scala方式
  //方式一
  val source : BufferedSource = scala.io.Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/read.txt", "UTF-8" )
  val value : Iterator[String] = source.getLines ()
  while (value.hasNext) {
  val str : String = value.next ()
  println ( str )
    //方式二
    scala.io.Source.fromFile ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/read.txt", "UTF-8" ).getLines ().foreach ( println )

    println ( "------------------" )
    //从文件中读取 Java方式
    val fileInputStream : FileInputStream = new FileInputStream ( new File ( "/Users/lucienoz/Workspaces/learn/MavenTest/ScalaTest/input/read.txt" ) )
    val inputStreamReader = new InputStreamReader ( fileInputStream, StandardCharsets.UTF_8 )
    val reader = new BufferedReader ( inputStreamReader )
    var flag : Boolean = true
    while (flag) {
      var line = reader.readLine ()
      if (line == null) {
        flag = false
      } else {
        println ( line )
      }
    }
  }
  }


  /**
   *字符串拼接
   */
  def StringTypeTest : Unit = {
    //Scala标识符
    var ++ : String = ""
    var -- = ""
    var @@ = ""
    //     var `` = ""
    var `""` = "ewrwer"
    println ( `""` )

    //字符串
    var a = "a"
    var b = "b"
    //字符串+连接
    println ( a + b )
    //字符串占位符连接
    printf ( "a=%s,b=%s", a, b )
    println ()
    //字符串插值连接
    println ( s"a=${a},b=$b" )
    //字符传多行
    var sql : String =
      """
        @select
        @*
        @from a t
        @where a.x = 'c'
        @and a.b = 'v'||'X'
        @""".stripMargin ( '@' )
    println ( sql )
  }

}
