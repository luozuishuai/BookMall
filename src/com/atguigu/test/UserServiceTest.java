package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:52
 */
public class UserServiceTest {

    private UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","666666","bbj168@qq.com"));
        userService.registUser(new User(null,"abc168","666666","abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "bbj168", "666666", null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("zuishuai")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用！");
        }
    }
    @Test
    public void existsEmail() {
        if(userService.existsEmail("zuishuai@qq.com")){
            System.out.println("该邮箱已存在");
        }else{
            System.out.println("该邮箱可用！");
        }
    }
}