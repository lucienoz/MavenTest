package com.atguigu.scalafor

import scala.Console.println
import scala.util.control.Breaks

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/24.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaFor {
    def main(args : Array[String]) : Unit = {
        for (i <- 1 to 9) {
            println ( i + "|" + " " * (9 - i) + "*" * (2 * i - 1) )
        }

        for (i <- 1 to 3; j <- 4 to 6 if i != 2 && j != 5) {
            println ( s"i=$i,j=$j" )
        }

        for (i <- 1 until 9) {
            println ( i + "|" + " " * (9 - i) + "*" * (2 * i - 1) )
        }

        val value : IndexedSeq[Int] = for (i <- 1 to 9) yield {
            i * 2
        }

        println ( value )

        //for continue用法
        for (i <- 1 to 9) {
            Breaks.breakable {
                if (i == 4) {
                    Breaks.break
                }
                println ( i )
            }

        }

        //for break用法
        Breaks.breakable (
            for (i <- 1 to 9) {
                if (i == 4) {
                    Breaks.break
                }
                println ( i )
            }
        )



    }

}
