package com.hongtao.serversso.controller;

import com.hongtao.serversso.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wanghongtao
 * @Date : 2020/7/7 21:03
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger("sso");

    @RequestMapping("/login")
    public User login(@RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            logger.info(user.getName()+"login success!");
        }
        return user;
    }

    @RequestMapping("/logout")
    public Boolean logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        logger.info(subject.getPrincipal()+"loout success!");
        return true;
    }

    @RequestMapping("/getLoginInfo")
    public User get(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

}