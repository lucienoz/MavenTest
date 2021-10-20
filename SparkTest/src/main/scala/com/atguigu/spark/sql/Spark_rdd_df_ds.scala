package com.atguigu.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession, functions}

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/16.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object Spark_rdd_df_ds {
	def main(args : Array[String]) : Unit = {

		val spark : SparkSession = SparkSession.builder ().config ( new SparkConf ().setAppName ( "test" ).setMaster ( "local[*]" ) ).getOrCreate ()
		val sc : SparkContext = spark.sparkContext

		import spark.implicits._
		val rdd : RDD[(Int, String, Int)] = sc.makeRDD ( List ( (1001, "zhangsan", 32), (1002, "lisi", 28), (1003, "wangwu", 20), (1004, "zhaoliu", 18) ) )
		val rddToDF : DataFrame = rdd.toDF ( "id", "name", "age" )

		println("="*40)
		rddToDF.show()

		val DFToDS : Dataset[User] = rddToDF.as[User]

		println("="*40)
		DFToDS.show()

		val rddToDS : Dataset[User] = rdd.map { case (id, name, age) => User ( id, name, age ) }.toDS ()
		rddToDS.createTempView("user")

		println("="*40)
		spark.udf.register("printAge",(age:Int)=> "age="+age)

		spark.sql ( "select id,'>>'||name,printAge(age) from user where id > 1001" ).show()


//		spark.udf.register("my_avg",functions.udaf())
//		spark.sql ( "select my_avg(age) from user where id > 1001" ).show()



	}

}
case class User(id:Int,name:String,age:Int)
