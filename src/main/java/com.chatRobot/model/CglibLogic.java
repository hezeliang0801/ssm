package com.chatRobot.model;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibLogic implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理人正在谈判中");
        methodProxy.invokeSuper(o,objects);
        System.out.println("代理人正在收款中");
        return null;
    }
}
