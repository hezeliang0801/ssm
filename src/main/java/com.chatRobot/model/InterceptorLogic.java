package com.chatRobot.model;

import com.chatRobot.interfaces.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterceptorLogic implements InvocationHandler {
    private Object object = null;
    //代理逻辑内添加一个真实对象的引用，从而实现代理逻辑与真实对象的绑定
    private Interceptor myInterceptor = null;
    //现在的代理逻辑类加入了一个我们设计好的拦截器，用来更好地帮我们丰富代理逻辑
 
 
    public InterceptorLogic(Object object, Interceptor myInterceptor) {
        //绑定真实对象和拦截器
        this.object = object;
        this.myInterceptor = myInterceptor;
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (myInterceptor == null) {
            //如果没有给代理逻辑类配置一个拦截器的话，则直接反射真实对象的方法
            return method.invoke(object, args);
        }
 
        if (myInterceptor.before(proxy, object, method, args) == false) {
            //调用前置方法，如果前置方法返回true则可以通过反射调用真实对象的方法
             method.invoke(object, args);
        } else {
            myInterceptor.around(proxy, object, method, args);
            //如果前置方法返回false，则不能调用真实对象的方法，而是调用around方法
        }
        myInterceptor.after(proxy, object, method, args);
        //无论前置方法返回true或false，after方法在最后一定会被调用
        return null;
    }
}