package com.atguigu.scalapartialfunction

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaPartialFuncTest {
  def main(args : Array[String]) : Unit = {
    val list : List[Any] = List ( 1, 2, 3, 4, 5, "test" )
    val pf : PartialFunction[Any, Any] = {
      case ele : Int => ele+1
    }
//    println ( list.map ( pf ) )

    println ( list.collect ( pf ) )


  }

}
