package com.chatRobot.Proxy.JdkProxy;

import com.chatRobot.Proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    public Person target;

    public Object getInstance(Person person){
        Class<?> clazz = person.getClass();
        this.target = person;
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("老子来代理");
        method.invoke(target,args);
        System.out.println("老子代理结束");
        return null;
    }
}
