package com.chatRobot.dao;

import com.sun.org.apache.xpath.internal.operations.String;

public class VolatileDemo {
    private static volatile boolean isOver = false;
    private static int a = 1;
    public static void main(String[] args) {
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver){
                    a=a+1;
                    System.out.println(a);
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;*/
    }
}