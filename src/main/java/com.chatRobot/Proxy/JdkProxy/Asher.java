package com.chatRobot.Proxy.JdkProxy;

import com.chatRobot.Proxy.Person;

public class Asher implements Person {

    public int number = 12;

    public Asher(int number) {
        this.number = number;
    }

    public Asher() {
    }

    @Override
    public void findGirls(int count) {
        if(count>18){
            System.out.println("Asher到了找女朋友的年纪");
        }else {
            System.out.println("小屁孩一个，找什么女朋友");
        }
    }

    @Override
    public void findWife(int count) {
        if(count>25){
            System.out.println("Asher到了找老婆的时候");
        }else {
            System.out.println("太早了，男人还是要先有事业");
        }

    }
}
