package com.chatRobot.Proxy.CglibProxy;

import com.chatRobot.Proxy.JdkProxy.Asher;
import com.chatRobot.Proxy.Person;

public class CglibProxyTest {
    public static void main(String[] args) {
        Person instance = (Person) new CglibProxy().getInstance(new Asher().getClass());
        instance.findGirls(20);
    }
}
