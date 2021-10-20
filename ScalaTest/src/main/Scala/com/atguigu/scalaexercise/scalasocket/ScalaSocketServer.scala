package com.atguigu.scalaexercise.scalasocket



import java.io.{BufferedReader, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.{ServerSocket, Socket}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/23.
 * Copyright © 2021 lucienoz. All rights reserved.
 */

object ScalaSocketServer {
  def main(args : Array[String]) : Unit = {
    val serverSocket = new ServerSocket ( 9999 )
    while(true) {
      val clientSocket : Socket = serverSocket.accept ()
      handleClentSocket ( clientSocket )
    }
  }

  def handleClentSocket(clientSocket:Socket):Unit={
    println("新客户端连接进入")
      new Thread(
        new Runnable {
          override def run() : Unit = {
            val inputStreamReader = new InputStreamReader(clientSocket.getInputStream(),java.nio.charset.StandardCharsets.UTF_8);
            val bufferedReader = new BufferedReader ( inputStreamReader )
            var flag = true

              println("读消息")
              var line = bufferedReader.readLine()
                println(line)
            val printWriter = new PrintWriter ( new OutputStreamWriter ( clientSocket.getOutputStream (), "UTF-8" ), true )
            printWriter.println("Byebye")
            bufferedReader.close()
            printWriter.close()
            clientSocket.close()
          }
        }
      ).start()
  }

}
