package com.atguigu.scalaexercise.scalasocket


import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.Socket
import java.nio.charset.StandardCharsets

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/23.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaSocketClient {
  def main(args : Array[String]) : Unit = {
    val socket : Socket = new Socket ( "localhost", 9999 )

    val printWriter = new PrintWriter ( new OutputStreamWriter ( socket.getOutputStream, java.nio.charset.StandardCharsets.UTF_8  ), true )
    printWriter.println(scala.io.StdIn.readLine("input a line >")+"\n")
    printWriter.flush()

    val bufferedReader = new BufferedReader ( new InputStreamReader ( socket.getInputStream, java.nio.charset.StandardCharsets.UTF_8 ) )


      var lineline = bufferedReader.readLine()
        println(lineline)


    bufferedReader.close()
    printWriter.close()
    socket.close()




  }

}
