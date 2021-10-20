package com.atguigu

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object recursionfunc {
  def main(args : Array[String]) : Unit = {
    def sum(num : Int) : Int = {
      if (num == 1)  1 else  num + sum ( num - 1 )
    }

    println ( sum ( 1000 ) )

    def sum1(num : Int,result:Int) : Int = {
      if (num == 1)  result else  sum1(num - 1,result + num)
    }

    println ( sum1 ( 1000000, 1 ) )


  }


}
