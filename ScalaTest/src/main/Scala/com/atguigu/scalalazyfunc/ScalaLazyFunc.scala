package com.atguigu.scalalazyfunc

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaLazyFunc {
  def main(args : Array[String]) : Unit = {
    def foo(bar:String):String={
      s"${bar}"
    }

    println("-----------1----------------")
    lazy val str : String = foo ( "bar" )
    println("-----------2----------------")
    println ( str )
    println("-----------3----------------")
    println("-----------4----------------")
  }

}
