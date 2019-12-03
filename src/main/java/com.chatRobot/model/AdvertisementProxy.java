package com.chatRobot.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AdvertisementProxy implements InvocationHandler {

    private Dilireba dilireba = null;

    public AdvertisementProxy(Dilireba dlrb) {
        this.dilireba = dlrb;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理人正在谈判中");
        method.invoke(dilireba,args);
        System.out.println("代理人正在收款中");
        return null;
    }
}
