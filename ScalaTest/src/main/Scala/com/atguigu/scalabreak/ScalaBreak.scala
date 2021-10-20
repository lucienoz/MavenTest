package com.atguigu.scalabreak

import scala.util.control.Breaks

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaBreak {
  def main(args : Array[String]) : Unit = {
    Breaks.breakable(
      for(i <- 1 to 5){
        if (i == 3) {
          Breaks.break ()
        }
        println(s"i=${i}")
      }
    )
    println("---------------")


    def oper(op: => String):String = {
      op
      "-"
    }


    var str = "str"
    val str1 : String = oper {
      str
    }
    println(str1)

    println("---------------+++++++++++++++++++++================")

  }

}
