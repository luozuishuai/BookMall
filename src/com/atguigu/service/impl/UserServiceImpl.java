package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:48
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.QueryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.QueryUserByUsername(username) == null) {
            //等于null，说明没有查到，表示该用户名可用
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existsEmail(String email) {
        if (userDao.QueryUserByEmail(email) == null) {
            //等于null，说明没有查到，表示该邮箱可用
            return false;
        } else {
            return true;
        }
    }
}
