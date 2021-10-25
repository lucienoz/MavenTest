package com.atguigu.sparkstreamingexample

import java.sql.Connection
import scala.collection.mutable.ListBuffer

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/21.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object MockerRealTime {

	private val cityInfoes : ListBuffer[CityInfo] = setCityInfos
	private val productInfos : ListBuffer[ProductInfo] = setProductInfos
	private val userInfoes : ListBuffer[UserInfo] = new ListBuffer[UserInfo]()

	private def setCityInfos:ListBuffer[CityInfo]={
		val conn : Connection = JdbcUtil.getConnection
		val jDBCResultSet : JdbcUtil.JDBCResultSet = JdbcUtil.executeQuery ( conn,
			"""
				|select * from city_info
				|""".stripMargin, Array () )
		val infoes : ListBuffer[CityInfo] = new ListBuffer[CityInfo]
		while (jDBCResultSet.resultSet.next ()) {
			infoes.append(CityInfo(jDBCResultSet.resultSet.getString(1),
				jDBCResultSet.resultSet.getString(2),
				jDBCResultSet.resultSet.getString(3)))
		}
		jDBCResultSet.close()
		conn.close()
		infoes
	}

	private def setProductInfos:ListBuffer[ProductInfo]={
		val conn : Connection = JdbcUtil.getConnection
		val jDBCResultSet : JdbcUtil.JDBCResultSet = JdbcUtil.executeQuery ( conn,
			"""
				|select * from product_info
				|""".stripMargin, Array () )
		val infoes : ListBuffer[ProductInfo] = new ListBuffer[ProductInfo]
		while (jDBCResultSet.resultSet.next ()) {
			infoes.append(ProductInfo(jDBCResultSet.resultSet.getString(1),
				jDBCResultSet.resultSet.getString(2),
				jDBCResultSet.resultSet.getString(3)))
		}
		jDBCResultSet.close()
		conn.close()
		infoes
	}


	private def setUserInfoes:ListBuffer[UserInfo]={
		val conn : Connection = JdbcUtil.getConnection
		val jDBCResultSet : JdbcUtil.JDBCResultSet = JdbcUtil.executeQuery ( conn,
			"""
				|select * from user
				|""".stripMargin, Array () )
		val infoes : ListBuffer[UserInfo] = new ListBuffer[UserInfo]
		while (jDBCResultSet.resultSet.next ()) {
			infoes.append(UserInfo(jDBCResultSet.resultSet.getString(1),
				jDBCResultSet.resultSet.getString(2)))
		}
		jDBCResultSet.close()
		conn.close()
		infoes
	}

	/**
	 * 模拟的数据
	 *
	 * 格式 ：timestamp area city userid adid
	 * 某个时间点 某个地区 某个城市 某个用户 某个广告
	 */






}
