package com.chatRobot.service.impl;

import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

    @Override
    public User addAccount(long id, long count){
        Map<String,Long> map= new HashMap();
        map.put("id",id);
        map.put("count",count);
        Long aLong = this.userDao.addAccount(map);
//        int i = 5/0;
        return this.userDao.selectUser(id);
    }

    @Override
    public void login(String username, String password, HttpServletRequest request) {
        Map<String,String> map= new HashMap();
        map.put("username",username);
        map.put("password",password);
        User i = this.userDao.checkLogin(map);
        HttpSession session = request.getSession(); //获取session对象
        session.setAttribute("username",username);  //向session域中保存信息
        session.setAttribute("password",password);
    }

    @Override
    public void save(String email) {
        this.userDao.save(email);
    }

}