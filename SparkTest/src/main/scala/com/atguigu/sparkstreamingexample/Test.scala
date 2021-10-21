package com.atguigu.sparkstreamingexample

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/20.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object Test {
	def main(args : Array[String]) : Unit = {
		val ints : Seq[Int] = RandomIntList.getRandomList ( 10, 10 )
		println ( ints.mkString ( "," ) )
	}

}
