package com.chatRobot.model;

import com.chatRobot.interfaces.Interceptor;

import java.lang.reflect.Method;

public class AgentInterceptor implements Interceptor {
    //这是经纪人拦截器的工作逻辑
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【经纪人before方法】经纪人正在与您谈判前期工作");
        return true;
    }
 
    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【经纪人around方法】经纪人before方法返回false，本方法被调用");
    }
 
    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【经纪人after方法】无论经纪人before方法返回true或false，本方法都会被调用");
    }
}
