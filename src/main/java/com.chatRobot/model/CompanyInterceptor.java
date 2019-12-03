package com.chatRobot.model;

import com.chatRobot.interfaces.Interceptor;

import java.lang.reflect.Method;

public class CompanyInterceptor implements Interceptor {
    //这是传媒公司拦截器的拦截逻辑
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【传媒公司before方法】传媒公司正在与您谈判前期工作");
        return true;
    }
 
    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【传媒公司around方法】传媒公司before方法返回false，本方法被调用");
    }
 
    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【传媒公司after方法】无论传媒公司before方法返回true或false，本方法都会被调用");
    }
}