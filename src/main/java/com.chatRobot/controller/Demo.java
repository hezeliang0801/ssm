package com.chatRobot.controller;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws Exception {
        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
        //2获取show()方法
        Method m = stuClass.getMethod(getValue("methodName"));//show
        //3.调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());
        System.out.println("============截断=============");
        ArrayList<String> list = new ArrayList<>();
        list.add("0000");
        Class<? extends ArrayList> aClass = list.getClass();
        ArrayList arrayList = aClass.getConstructor().newInstance();
        Method add = aClass.getMethod("add", Object.class);
        add.invoke(arrayList,"1000");
        for (String s : list) {
            System.out.println(111);
            System.out.println(s);
        }
        System.out.println(arrayList==list?"你好":"你不好");
    }

    //此方法接收一个key，在配置文件中获取相应的value
    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        String path = "F:\\ChatRobot\\src\\main\\java\\com.chatRobot\\controller\\pro.txt";
        FileReader in = new FileReader(path);//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }
}