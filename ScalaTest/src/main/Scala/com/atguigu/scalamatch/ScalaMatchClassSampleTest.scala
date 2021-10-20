package com.atguigu.scalamatch

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaMatchClassSampleTest {

  def main(args : Array[String]) : Unit = {
    val useer01 : Useer = Useer ( "zhangsan" )
    println ( useer01 match {
      case Useer ( "zhangsan" ) => "yes"
      case _ =>"No"

    } )

  }

}

case class Useer(val name:String){

}

