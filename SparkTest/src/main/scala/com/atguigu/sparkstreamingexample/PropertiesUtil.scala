package com.atguigu.sparkstreamingexample

import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util
import java.util.Properties

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/10/20.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object PropertiesUtil {
	def main(args : Array[String]) : Unit = {
		val value : util.Enumeration[URL] = Thread.currentThread ().getContextClassLoader.getResources ( "hdfs-site.xml" )
		println ( value.nextElement() )
	}

	def load(propertiesName : String)  ={
		val prop = new Properties ()
		prop.load(new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName),StandardCharsets.UTF_8))
		prop
	}

}
