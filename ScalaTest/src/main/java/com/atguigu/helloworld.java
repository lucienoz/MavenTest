package com.atguigu;

import com.atguigu.scalaexception.ScalaExceptionTest;

import java.util.HashMap;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/22.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class helloworld {
    public static void main(String[] args) throws Exception {
        String[] arrgs = new String[10];
        System.out.println("arrgs.getClass() = " + arrgs.getClass());
        char c = 'A';
        for (int i = 0; i < 26; i++) {
            System.out.println((char)(c+i));
        }
        JavaUser javaUser = new JavaUser();
        JavaUser javaUser1 = javaUser.clone();
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());

        HashMap<String,String> hashMap = new HashMap<>();
        ScalaExceptionTest.testException();


    }
}
class JavaUser implements Cloneable{
    @Override
    protected JavaUser clone() throws CloneNotSupportedException {
        return (JavaUser) super.clone();
    }


}
