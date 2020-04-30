package com.chatRobot.dao;

import com.alibaba.fastjson.JSON;
import com.chatRobot.model.User;
import com.ebanma.cloud.gateway.client.BifrostClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.json.Json;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

//    @Autowired(required = false)
    private BifrostClient bifrostClient;
    @Before
    public void init() {
        bifrostClient = new BifrostClient("https://oapi-qa.ebanma.com:20016", "coupon",
                "2d223e65fb3a4273a7ab3ffc15a44ed4");
    }
    @Test
    public void test() throws Exception {
        bifrostClient = new BifrostClient("https://oapi-qa.ebanma.com:20016", "coupon",
                "2d223e65fb3a4273a7ab3ffc15a44ed4");
        bifrostClient.get("/gw/spCoupon/couponInfoBack/1.0", "{'mobileNo':'17621910063','couponCode': '1242233'}");
    }



}