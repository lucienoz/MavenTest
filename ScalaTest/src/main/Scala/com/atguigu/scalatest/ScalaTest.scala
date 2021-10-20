package com.atguigu.scalatest

import scala.::
import scala.collection.mutable

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/25.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaTest {
  def main(args : Array[String]) : Unit = {
    val userscala = new Userscala ()

    val userscala1 = new Userscala () with TraitTest

    val value : Class[_] = Class.forName ( "com.atguigu.scalatest.Userscala" )

    val clazz : Class[_ <: Userscala] = userscala.getClass
    val value1 : Class[Userscala] = classOf[Userscala]

    val value2 : Array[_] = new Array( 10 )
    println(value2)

    val value3 : List[Int] = List.apply ( 1, 2, 3, 4 )

    println ( value3.getClass.getName )

    val map : mutable.Map[String,String] = mutable.Map.apply();








  }
}
class Userscala{
  val str:String = "str"
  println(str)
  def this(str:String){
    this()
  }

}

abstract class AbstractScala{
  var str:String
  val name : String
  def abstractMethod
}

class AbstractScalaImpl extends AbstractScala{
  override def abstractMethod : Unit = {
  }

  override var str : String = _
  override val name : String = "name"

}

