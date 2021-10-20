package com.atguigu.scalamatch

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaMatchTest {

  var a = 1

  var b = 2

  def main(args : Array[String]) : Unit = {
    var operator = '*'
    val value : Any = operator match {

      case '+' => a + b

      case '-' => a - b

      case '*' => a * b

      case '/' => a / b

      case _ => "illegal"

    }
    println(value)

    println ( 1 :: 2 :: List ( 3, 4 ):::4::Nil)


  }

}
