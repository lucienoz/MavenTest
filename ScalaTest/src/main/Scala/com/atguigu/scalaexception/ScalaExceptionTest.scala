package com.atguigu.scalaexception

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaExceptionTest {
  def main(args : Array[String]) : Unit = {
      try{
        var a : Int = 8
        var b : Int = 0
        val i : Int = a / b
      }catch {
        case exception: ArithmeticException => println(exception)
        case exception: NullPointerException => println(exception)
        case exception => println(exception)
      }finally {
        println("ok")
      }
  }
  @throws[Exception]
  def testException={
    throw new RuntimeException
  }
}
