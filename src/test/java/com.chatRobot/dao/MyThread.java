package com.chatRobot.dao;

public class MyThread extends Thread{//继承Thread类

    public void run(){

    //重写run方法

    }
    public static void main(String[] args){

        new MyThread().start();//创建并启动线程

    }


}