package com.atguigu.scalafunc

import com.atguigu.scalatest.ScalaClass
import sun.tools.jconsole.Plotter

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaFunc {

  def foo() : Unit = {

  }

  def foo(str : String) : String = {
    "bar"
  }

  def main(args : Array[String]) : Unit = {
    /*def foo():Unit = {
      println("bar")
    }
    foo()*/

    /* def foo():String={
       "bar1"
     }*/

    //    println ( foo() )

    /*def foo(bar:String):String={
      bar
    }*/

    //    foo("bar")

    /*def foo(bar:String,num :Int):Unit={
      println(s"$bar\t"*num)
    }
    foo("bar",5)*/

    /*def foo(bar:String,num:Int):String={
      s"$bar\t"*num
    }
    println ( foo ( "bar", 5 ) )*/

    /*def foo(num:Int,strs:String*):Unit={
      strs.foreach(println _ )
      println(num)
    }

    foo(5,"bar","bar")
    foo(5)*/

    //    def foo(bar:String,num:Int = 1,bars:String*):Unit={
    //
    //    }

    //    def foo(bar:String ,num:Int = 5):String={
    //      s"$bar\t"*num
    //    }
    //
    //    println ( foo ( "bar", 6 ) )
    //    println ( foo ( "bar" ) )

    //    def foo(bar :String):String={
    //      s"$bar"
    //    }
    //    println ( foo ( "test" ) )

    //    def foo(bar:String):String = s"$bar"
    //    println ( foo ( "test" ) )

    //    def foo(bar:String ,num:Int = 5,bool:Boolean):String = if(bool) s"$bar\t"*num else s"$bar"
    //
    //    println ( foo ( "bar", bool = false ) );

    //    def foo:String=>String = {
    //      bar=>bar*5
    //    }
    //
    //    val foo1 : String => String = foo
    //    println ( foo1("bar\t") )

    /*def foo(bar : () => Unit) : Unit = {
      bar ()
    }

    foo ( () => println ( "bar" ) )*/

  /*  def foo(bar:(String,Int)=>String): Unit ={
      println(bar("bar",6))
    }

    foo(  _ * _ )*/

    /*def foo(bar:(String,String)=>String):Unit={
      println ( bar ( "foo", "bar" ) )
    }

    foo(_.toUpperCase + _.toUpperCase)*/


    val scalaClass = new ScalaClass ()



  }

}
