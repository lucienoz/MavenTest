package com.atguigu.sparkstreamingexample

import com.alibaba.druid.pool.DruidDataSource

import java.sql.{Connection, PreparedStatement}
import java.util.Properties
import javax.sql.DataSource

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/20.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object JdbcUtil {

	private val dataSource : DataSource = init ()

	def  init() : DataSource ={
		val source : DruidDataSource = new DruidDataSource ()
		val properties = new Properties ()
		val prop : Properties = PropertiesUtil.load ( "config.properties" )
		properties.setProperty("url",prop.getProperty("jdbc.url"))
		properties.setProperty("username",prop.getProperty("jdbc.user"))
		properties.setProperty("password",prop.getProperty("jdbc.password"))
		properties.setProperty("maxActive",prop.getProperty("jdbc.datasource.size"))

		source.setConnectProperties(properties)
		source
	}

	def getConnection : Connection ={
		dataSource.getConnection
	}

	def executeUpdate(connection: Connection,sql:String,arrParams: Array[Any]):Int={
			var rtn:Int = 0
		try {
			connection.setAutoCommit ( false )
			val preparedStatement : PreparedStatement = connection.prepareStatement ( sql )
			var i : Int = 0
			for (elem <- arrParams) {
				i += 1
				preparedStatement.setObject ( i, elem )
			}
			rtn = preparedStatement.executeUpdate ()
			connection.commit()
		} catch {
			case e:Exception => e.printStackTrace()
		}
		rtn
	}

	def executeBatchUpdate(connection: Connection,sql:String,arrParams: Iterable[Array[Any]]) : Array[Int] ={
		var rtns : Array[Int] = Array[Int]
		try {
			connection.setAutoCommit ( false )
			val preparedStatement : PreparedStatement = connection.prepareStatement ( sql )
			for (params <- arrParams) {
				var i : Int = 0
				for (elem <- params) {
					i += 1
					preparedStatement.setObject ( i, elem )
				}
				preparedStatement.addBatch ()
			}
			rtns  = preparedStatement.executeBatch ()
			connection.commit()
			preparedStatement.close()
		} catch {
			case e:Exception => e.printStackTrace()
		}
		rtns
	}









}
