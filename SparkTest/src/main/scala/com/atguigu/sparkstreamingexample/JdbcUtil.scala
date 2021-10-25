package com.atguigu.sparkstreamingexample

import com.alibaba.druid.pool.{DruidDataSource, DruidDataSourceFactory}

import java.io.Closeable
import java.sql.{Connection, PreparedStatement, ResultSet}
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

	private def  init() : DataSource ={
		val properties = new Properties ()
		val prop : Properties = PropertiesUtil.load ( "config.properties" )
		properties.setProperty("url",prop.getProperty("jdbc.url"))
		properties.setProperty("username",prop.getProperty("jdbc.user"))
		properties.setProperty("password",prop.getProperty("jdbc.password"))
		properties.setProperty("maxActive",prop.getProperty("jdbc.datasource.size"))

		DruidDataSourceFactory.createDataSource(properties)
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
		var rtns : Array[Int] = Array[Int]()
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

	case class JDBCResultSet(preparedStatement:PreparedStatement,resultSet :ResultSet) extends Closeable{
		override def close() : Unit = {
			resultSet.close()
			preparedStatement.close()
		}
	}

	def executeQuery(connection: Connection,sql:String,arrParams: Array[Any]) : JDBCResultSet ={
		try {
			val preparedStatement : PreparedStatement = connection.prepareStatement ( sql )
			var i : Int = 0
			for (elem <- arrParams) {
				i += 1
				preparedStatement.setObject ( i, elem )
			}
			val resultSet : ResultSet = preparedStatement.executeQuery ()
			JDBCResultSet(preparedStatement,resultSet)
		} catch {
			case e:Exception => e.printStackTrace()
			null
		}
	}

	def isExists(connection: Connection,sql:String,arrParams: Array[Any]) : Boolean ={
		var flag : Boolean = false
		try {
			val preparedStatement : PreparedStatement = connection.prepareStatement ( sql )
			var i : Int = 0
			for (elem <- arrParams) {
				i += 1
				preparedStatement.setObject ( i, elem )
			}
			val resultSet : ResultSet = preparedStatement.executeQuery ()
			flag = resultSet.next ()
			preparedStatement.close()
			resultSet.close()
		} catch {
			case e:Exception => e.printStackTrace()
		}
		flag
	}









}
