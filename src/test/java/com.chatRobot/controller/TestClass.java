package com.chatRobot.controller;

public class TestClass {
    private void m(){
        int i = 5;
        i = i++;
        System.out.println(i);
    }
    public static void main(String[] args) {
        new TestClass().m();
    }
}
