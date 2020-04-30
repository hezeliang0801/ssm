package com.chatRobot.controller;

import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User updateById(@RequestParam("id")Integer id){
        User user = this.userService.selectUser(id);
        return user;
    }

    @RequestMapping(value="/ds", method = RequestMethod.GET)
    @ResponseBody
    public void updateById(){
        String email = "sdljf sdj"+"\\n"+ "sjidfj";
        this.userService.addAccount(1,5);
    }

    @RequestMapping(value="/addAccount", method = RequestMethod.POST)
    @ResponseBody
    public User addAccountById(@RequestParam("id")Integer id,@RequestParam("count") Integer count){
         return this.userService.addAccount(id,count);
    }



    /*@RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestParam("username")String username, @RequestParam("password")String password,HttpServletRequest request){
        return this.userService.login(username,password,request);
    }*/
    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        this.userService.login(username,password,request);
    }

    public static void main(String[] args) throws IOException {
        Integer i = new Integer(200);
        boolean equals = ObjectUtils.equals(200,i);
        System.out.println(equals);
    }







}