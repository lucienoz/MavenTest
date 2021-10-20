package com.atguigu.scalamatch

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/7.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaMatchClassTest {
  def main(args : Array[String]) : Unit = {
    val user01 = new User ( "zhangsan", 16 )
    val str : String = user01 match {
      case User ( "zhangsan", 16 ) => "yes"
      case _ => "no"
    }
    println ( str )
  }

}

class User(var name:String,var age:Int){

}
object User{
  def unapply(user : User) : Option[(String, Int)] = {
    if(user == null)
      None
    else
      Some(user.name,user.age)
  }

}
