package com.chatRobot.controller;

import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "测试专用")
    @RequestMapping(value="/test",method=RequestMethod.GET)
    @ResponseBody
    public User test(@RequestParam("id")Integer id){
        User user = this.userService.selectUser(id);
        return user;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public User updateById(@PathVariable("id")Integer id){
        User user = this.userService.selectUser(id);
        return user;
    }

}