package com.atguigu.scalacollections

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/27.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaListTest {
  def main(args : Array[String]) : Unit = {
    //immutable - List

    val list1 : List[Int] = List.apply ( 1, 2, 3, 4, 5 )
    val list2 : List[Int] = List.apply ( 5,6,7,8,9,0 )

    println(list1)
    println(list2)

    println ( list1 ( 0 ) )
    println ( list2 ( 0 ) )

    val nil : Nil.type = Nil

    var list3 = 1:: 2 :: 4:: 5:: list1 :::Nil

    println(list3)

    val list4 : List[Int] = list1 ++ list2

    println(list4.distinct)

    val list5 : List[Int] = list1 .++: (list2)
    println(list5)


    val value : AnyVal = list5.find ( _int => if (_int == 11) true else false ).getOrElse ()

    if (value.isInstanceOf[Int]) {
      println ( value.asInstanceOf[Int] )
    }

    println(list3) //List(1, 2, 4, 5, 1, 2, 3, 4, 5)

    val value1 : List[Int] = list3.distinctBy ( int => {
      if (int % 2 == 0) {
        0
      } else {
        1
      }
    } )

    println ( value1 ) //List(1, 2)

    //mutable -- ListBuffer

    var listBuff1 : ListBuffer[Int] = new ListBuffer[Int]()
    val listBuff2 : ListBuffer[Int] = ListBuffer[Int]( 1, 2, 3, 4, 5, 6 )
    listBuff1.appendAll(list2)

    println(listBuff1)
    println(listBuff2)

    val value2 : ListBuffer[Int] = listBuff2 ++ listBuff1

    listBuff1.appendAll(listBuff2)

    println(listBuff1)
    println(value2)

    val list : List[Int] = listBuff1.toList
    val buffer : mutable.Buffer[Int] = list1.toBuffer

    








  }

}
