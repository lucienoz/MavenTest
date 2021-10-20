package com.atguigu.scalaparallelmap

import scala.collection.parallel.CollectionConverters.RangeIsParallelizable

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaParallelMap {
  def main(args : Array[String]) : Unit = {
    val result1 = (0 to 100).map{x => Thread.currentThread.getName}
    val result2 = (0 to 100).par.map(x => Thread.currentThread.getName)

    println(result1)
    println(result2)
  }

}
