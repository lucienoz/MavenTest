package com.atguigu.scalaloop

import java.lang.Math.round
import java.nio.charset.StandardCharsets

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaLoopTest {
  def main(args : Array[String]) : Unit = {
    for (i <- 1 to 5 if i != 4) {
      println ( i )
    }
    println ( "--------------------------" )
    for (i <- 1 until 5 if i != 4) {
      println ( i )
    }

    println ( "--------------------------" )
    val yieldLoop1 : IndexedSeq[Int] = yieldLoop

    println(yieldLoop1)
    println ( "--------------------------" )

    for(i <- 1 to 9){
      println (" "* (9-i)+"*"*((2*i)-1) )
    }

    println ( "--------------------------" )

    for(i <- Range.inclusive(1,9,2)){
      println (" "* (9-i)+"*"*((2*i)-1) )
    }

  }

  private def yieldLoop : IndexedSeq[Int] = {
    for (i <- 1 to 5 if i != 4) yield {
      i
    }
  }




}
