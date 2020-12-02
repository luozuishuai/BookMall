package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:10
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 如果返回null 说明没有这个用户 反之亦然
     */
    User QueryUserByUsername(String username);

    User QueryUserByEmail(String email);

    /**
     * 保存用户信息
     *
     * @param user
     * @return 如果返回-1则表示操作失败 其他是SQL语句影响的行数
     */
    int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null 说明用户名或密码错误 反之亦然
     */
    User QueryUserByUsernameAndPassword(String username, String password);
}
