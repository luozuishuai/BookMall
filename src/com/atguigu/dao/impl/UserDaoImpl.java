package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 17:14
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public User QueryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User QueryUserByEmail(String email) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where email = ?";
        return queryForOne(User.class,sql,email);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User QueryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }
}
