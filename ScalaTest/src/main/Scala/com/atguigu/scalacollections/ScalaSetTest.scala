package com.atguigu.scalacollections

import scala.collection.mutable

/**
 *
 * Description:
 *
 * Create by lucienoz on 2021/9/28.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
object ScalaSetTest {

  def main(args : Array[String]) : Unit = {
    //immutable
    val set : Set[Int] = Set.apply[Int](10,10,20,100,30)
    val set2 : Set[Int] = Set.apply[Int](30)

    println ( set.mkString ( "," ) )
    val value : Set[Int] = set.removedAll ( set2 )

    println ( value.mkString ( "," ) )
    //mutable

    val mset : mutable.Set[Int] = mutable.Set[Int]();

    println ( mset.getClass.getName )

    mset.addAll(set)

    println(mset.mkString(","))

    mset.update(80,false)

    println(mset.mkString(","))

    val set1 : Set[Int] = mset.toSet

    mset.clear()
    mset.addAll(set1)
    println(mset.mkString(","))
  }

}
