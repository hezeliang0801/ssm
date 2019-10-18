package com.chatRobot.service;

import com.chatRobot.model.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    public User selectUser(long userId);

    User addAccount(long id, long count);

    void login(String username, String password, HttpServletRequest request);

    void save(String user);
}