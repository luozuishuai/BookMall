package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:19
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.QueryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "luozuishuai", "abc123", "zuishuai@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.QueryUserByUsernameAndPassword("luozuishuai", "abc123") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("登录成功");
        }
    }
}