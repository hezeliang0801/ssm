package com.chatRobot.dao;

import com.chatRobot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

    @Autowired
    private IUserDao dao;

    @Test
    public void testSelectUser() throws Exception {
        long id = 2;
        User user = dao.selectUser(id);
        System.out.println(user.getEmail());
    }

    @Test
    public void checkLogin() throws Exception {
        Map<String,String> map= new HashMap();
        map.put("username","miku01");
        map.put("password", "hzl123miku");
        User user = dao.checkLogin(map);
        System.out.println(user.getAccount());


    }

}