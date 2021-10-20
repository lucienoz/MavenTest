package com.atguigu.scalatailrecursive

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaTailRecursive {
  def main(args : Array[String]) : Unit = {
    def pow(num:Int,times:Int,result:Int=1):Int={
      if(times==0){
        return result
      }else{
        return pow(num, times-1, result*num)
      }
    }

    println ( pow ( 2, 3 ) )

    /**
     * pow(2,3,1)
     * pow(2,2,1*2)
     * pow(2,1,1*2*2)
     * pow(2,0,1*2*2*2)
     * 1*2*2*2
     */
  }

}
