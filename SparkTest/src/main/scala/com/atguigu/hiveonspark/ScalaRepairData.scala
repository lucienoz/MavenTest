package com.atguigu.hiveonspark

import org.apache.spark.sql.SparkSession

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaRepairData {
	def main(args : Array[String]) : Unit = {
		System.setProperty("HADOOP_USER_NAME", "raylu")
		val spark : SparkSession = SparkSession.builder ().master ( "local[*]" )
			.appName ( "sql" )
			.enableHiveSupport ()
		  //.config("dfs.client.use.datanode.hostname", "true")
			.getOrCreate()

		import spark.implicits._

		spark.sql(
			"""
				|CREATE TABLE IF NOT EXISTS `user_visit_action`(
				|  `date` string,
				|  `user_id` bigint,
				|  `session_id` string,
				|  `page_id` bigint,
				|  `action_time` string,
				|  `search_keyword` string,
				|  `click_category_id` bigint,
				|  `click_product_id` bigint,
				|  `order_category_ids` string,
				|  `order_product_ids` string,
				|  `pay_category_ids` string,
				|  `pay_product_ids` string,
				|  `city_id` bigint)
				|row format delimited fields terminated by '\t'
				|""".stripMargin).show()
		spark.sql(
			"""
				|load data local inpath '/Users/lucienoz/Workspaces/learn/MavenTest/SparkTest/data/spark-hive/user_visit_action.txt' into table user_visit_action
				|""".stripMargin).show()

		spark.sql(
			"""
				|CREATE TABLE if not exists `product_info`(
				|  `product_id` bigint,
				|  `product_name` string,
				|  `extend_info` string)
				|row format delimited fields terminated by '\t'
				|""".stripMargin).show()

		spark.sql(
			"""
				|load data local inpath '/Users/lucienoz/Workspaces/learn/MavenTest/SparkTest/data/spark-hive/product_info.txt' into table product_info
				|""".stripMargin).show()

		spark.sql(
			"""
				|CREATE TABLE if not exists `city_info`(
				|  `city_id` bigint,
				|  `city_name` string,
				|  `area` string)
				|row format delimited fields terminated by '\t'
				|""".stripMargin).show()

		spark.sql(
			"""
				|load data local inpath '/Users/lucienoz/Workspaces/learn/MavenTest/SparkTest/data/spark-hive/city_info.txt' into table city_info
				|""".stripMargin).show()


		spark.stop()



	}

}
