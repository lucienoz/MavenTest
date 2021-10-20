package com.atguigu.scalafunc

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaCloseBox {
  def main(args : Array[String]) : Unit = {
    def outFunc(i:Int):Int=>Int={
      def innerFunc(j:Int):Int={
        return i + j
      }
      return innerFunc
    }

    println ( outFunc ( 10 )( 20 ) )

  }

}
