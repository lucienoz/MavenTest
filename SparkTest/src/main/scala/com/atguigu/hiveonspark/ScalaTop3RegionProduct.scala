package com.atguigu.hiveonspark

import org.apache.spark.sql.{SparkSession, functions}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaTop3RegionProduct {
	def main(args : Array[String]) : Unit = {
		System.setProperty("HADOOP_USER_NAME", "raylu")
		val spark : SparkSession = SparkSession.builder ().master ( "local[*]" )
			.appName ( "sql" )
			.enableHiveSupport ()
		 // .config("dfs.client.use.datanode.hostname", "true")
			.getOrCreate()

		spark.udf.register("getTop3",functions.udaf(new getnoteratebyregioncitys(3)))

		spark.sql(
			"""
				|with productbyregioncount as (
				|select c.area,
				|       b.product_name,
				|       count(0) as cnt,
				|				getTop3(c.city_name) as marks
				|  from user_visit_action a,
				|       product_info b,
				|       city_info c
				|where a.click_product_id = b.product_id
				|  and a.city_id = c.city_id
				|group by
				|       c.area,
				|       b.product_name),
				|     productbyregioncountrownum as (
				|         select a.area,
				|                a.product_name,
				|                a.cnt,
				|                rank() over (partition by a.area order by cnt desc) as rn,
				|                a.marks
				|           from productbyregioncount a
				|     ),
				|     productbyregioncounttop3 as (
				|         select *
				|           from productbyregioncountrownum a
				|          where a.rn <=3
				|         order by 1,4
				|     )
				|select * from productbyregioncounttop3
				|""".stripMargin).show(false)


		spark.stop()



	}

}
