package com.atguigu.autotransform

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaAutoTransformTest {
  def main(args : Array[String]) : Unit = {
    //1
    val i : Int = 2.0.toInt
    //2
    implicit def doubleAutoTransformToInt(d:Double):Int={
      d.toInt
    }
    val ii : Int = 2.0
  }

}
