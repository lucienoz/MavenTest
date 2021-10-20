package com.atguigu.scalatest

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/22.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object HelloWorld {

  def main(args: Array[String]): Unit = {
    System.out.println("Hello Java!");
    Predef.println("Hello Scala!");
    test()
  }


  def test(): Unit = {
    //定义变量
    var name: String = "helloworld";
    val passwd: String = "yourpasswd";

    //类型推断
    var age  = 16;
    //    age = "hello";

    var address: String = "text can change";
    address = "text can change!";
    Character.MAX_VALUE

    var str: String = "sdadasda";
    var test:Int = 1;

    val addr: String = "text cant't change"
    //    addr = "text cant't change!"

    var ~ = "asdasd";
    var * = 1;
    var @@ = 1;
    var ? = 1;

    var multiLine:String =
      """
        |adadad
        |adsas
        |qweqwe
        |fds
        |ffgsfg
        |shgfh
        |erteqwrq
        |q  ewqeqw  e q
        |asdaasfdsf
        |""".stripMargin

    println(multiLine)

  }

}
