package com.chatRobot.model;

import com.chatRobot.interfaces.Interceptor;

import java.lang.reflect.Method;

public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【before方法】正在与迪丽热巴的经纪人洽谈，谈判成功返回true，失败返回false");
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【around方法】与经纪人人谈判失败！本方法被调用！无法见到迪丽热巴！");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【after方法】无论谈判成功或失败，本方法都会被调用！");
    }
}