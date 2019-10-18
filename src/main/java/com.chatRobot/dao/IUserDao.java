package com.chatRobot.dao;

import com.chatRobot.model.User;

import java.util.Map;

public interface IUserDao {

    User selectUser(long id);

    Long addAccount(Map<String,Long> map);

    User checkLogin(Map<String, String> map);

    void save(String user);
}