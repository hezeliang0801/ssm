package com.chatRobot.Proxy.JdkProxy;

import com.chatRobot.Proxy.Person;

public class JdkProxyTest {
    public static void main(String[] args) throws Exception {
        Person asher = (Person) new JdkProxy().getInstance(new Asher(19));
        asher.findWife(20);
        asher.findGirls(20);
    }
}
