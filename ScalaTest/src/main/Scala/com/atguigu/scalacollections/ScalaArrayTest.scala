package com.atguigu.scalacollections

import scala.runtime.Nothing$
import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/27.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaArrayTest {
  def main(args : Array[String]) : Unit = {
    //immutable Array
    var ints : Array[Int] = new Array[Int]( 10 )
    ints.update ( 0, 0 )
    ints.update ( 1, 1 )
    ints.update ( 2, 2 )
    ints.update ( 3, 3 )
    ints.update ( 4, 4 )
    ints ( 5 ) = 5


    ints.foreach ( println )

    if (ints.size == ints.length) {
      println ( ints.size )
      println ( ints.length )
    }

    println ( ints.mkString )
    println ( ints.mkString ( "," ) )
    println ( ints.mkString ( "{", ",", "}" ) )

    val ints1 : Array[Int] = Array ( 1, 2, 3, 4 )
    val ints2 : Array[Int] = Array ( 4, 5, 6, 7, 8, 9 )

    val ints3 : Array[Int] = ints1 :+ 5
    val ints4 : Array[Int] = ints1.+: ( 5 )

    val str : String = ints1 + "5"

    val ints5 : Array[Int] = ints1 ++ ints2
    val ints6 : Array[Int] = ints1 ++: ints2
    val ints7 : Array[Int] = ints2 ++: ints1


    println ( str )
    println ( ints3.mkString ( "," ) )
    println ( ints4.mkString ( "," ) )
    println ( ints5.mkString ( "," ) )
    println ( ints6.mkString ( "," ) )
    println ( ints7.mkString ( "," ) )

    val array : Array[Array[Int]] = new Array[Array[Int]]( 4 )

    array.update ( 0, ints1 )
    array.update ( 1, ints2 )
    array.update ( 2, ints3 )
    array.update ( 3, ints4 )

    array.foreach ( a => {
      println ( "arrays:" + a.mkString ( "," ) )
    } )

    val array1 : Array[Array[Array[Int]]] = Array.ofDim ( 2, 3, 4 )

    for (elem <- array1) {
      for (elem <- elem) {
        print ( elem.mkString ( "{", ",", "}" ) )
      }
      println ()
    }

    val ints8 : Array[Int] = ints1
    println ( ints8.mkString ( "," ) )

    val ints9 : Array[Int] = Array.range ( 0, 100, 5 )
    println ( ints9.mkString ( "," ) )

    val array2 : Array[Array[Array[Int]]] = Array.fill ( 2, 2, 3 )( 100 )

    for (elem <- array2) {
      for (elem <- elem) {
        print ( elem.mkString ( "-{-", ",", "-}-" ) )
      }
      println ()
    }

    val ints10 : Array[Int] = ints
    println ( ints10.mkString ( "," ) )
    println ( ints.prepended ( -1 ).mkString ( "," ) )

    println ( util.Arrays.toString ( ints.prepended ( -1 ) ) )

    //mutable Array

    val bufArr = new ArrayBuffer[Int]( 10 )
    println ( "|" + bufArr.mkString ( "," ) + "|" )

    bufArr.append ( 1, 2 )
    val bufArr0 : ArrayBuffer[Int] = bufArr.appended ( 3 )
    println ( bufArr.prepended ( -1 ).mkString ( "," ) )

    println ( "|" + bufArr.mkString ( "," ) + "|" )
    println ( "|" + bufArr0.mkString ( "," ) + "|" )

    bufArr.appendAll ( ints )
    println ( "|" + bufArr.mkString ( "," ) + "|" )

    bufArr.prependAll ( ints1 )
    println ( "|" + bufArr.mkString ( "," ) + "|" )

    val value : ArrayBuffer[Int] = bufArr :+ 2
    val value1 : ArrayBuffer[Int] = bufArr.+: ( 2 )

    println ( value + "-" + value1 )

    val value2 : Array[Int] = bufArr ++: ints

    println ( value2.mkString ( "," ) )


    val bufArr1 : ArrayBuffer[Int] = new ArrayBuffer[Int]()

    val bufArr2 : ArrayBuffer[Int] = new ArrayBuffer[Int]()

    bufArr1.appendAll ( ints1 )

    bufArr2.appendAll ( ints2 )

    println ( bufArr1 )
    println ( bufArr2 )


    val value3 : ArrayBuffer[Int] = bufArr1.++: ( ints2 )
    println ( value3.mkString ( "," ) )

    val toArray : Array[Int] = bufArr1.toArray
    val buffer : mutable.Buffer[Int] = toArray.toBuffer

    if (buffer.isInstanceOf[ArrayBuffer[Int]]) {
      val value4 : ArrayBuffer[Int] = buffer.asInstanceOf[ArrayBuffer[Int]]
      println(value4)
    }

    println ( toArray ( 0 ) )
    println ( bufArr2 ( 0 ) )


  }


}
