package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:44
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null 则表示登录失败 返回有值 则表示登录成功
     */
    User login(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    boolean existsUsername(String username);
    /**
     * 检查邮箱地址是否存在
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    boolean existsEmail(String email);
}
