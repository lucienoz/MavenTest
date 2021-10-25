package com.atguigu.sparkstreamingexample

import java.sql.{Connection, ResultSet}
import java.util.Properties
import javax.sql.DataSource

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/20.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object Test {
	def main(args : Array[String]) : Unit = {
		val ints : Seq[Int] = RandomList.getRandomList ( 10, 10 )
		println ( ints.mkString ( "," ) )


		val conn : Connection = JdbcUtil.getConnection

		val jDBCResultSet : JdbcUtil.JDBCResultSet = JdbcUtil.executeQuery ( conn,
			"""
				|select * from user limit 0,?
				|""".stripMargin,
			Array ( 10 ) )

		while (jDBCResultSet.resultSet.next ()) {
			val userId : String = jDBCResultSet.resultSet.getString ( 1 )
			val userName : String = jDBCResultSet.resultSet.getString ( 2 )
			println(s"$userId --> $userName")
		}
		jDBCResultSet.close()

	}

}
