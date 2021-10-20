package com.atguigu;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/8/10.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class Demo02 {
    public void sayHello(){
        System.out.println("Demo02");
    }
    public String sayHello(String str){
        return str;
    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        demo02.sayHello();
        System.out.println(demo02.sayHello("Hello"));
    }
}
