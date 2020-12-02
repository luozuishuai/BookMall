package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 16:01
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用于执行：insert/update/delete语句
     *
     * @return 返回-1 则说明执行失败 否则表示sql语句影响行数
     */
    public int update(String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        /**
         *   @Author:luozuishuai
         *   @Desciption:用于查询数据库中的一条结果
         *   @params: * @param type 要查询的表格对应的Bean对象类型
         * @param sql SQL语句
         * @param args SQL中需要的参数
         @return:
          *   @Date: 17:05
         */
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        /**
         *   @Author:luozuishuai
         *   @Desciption:用于查询数据库中的多条结果
         *   @params: * @param type 要查询的表格对应的Bean对象类型
         * @param sql SQL语句
         * @param args SQL中需要的参数
         @return:
          *   @Date: 17:05
         */
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Object queryForSingleValue(String sql, Object... args) {
        /**
         *   @Author:luozuishuai
         *   @Desciption:执行返回一行一列的sql语句
         *   @params: * @param sql sql语句
         * @param args sql语句中需要的参数
         @return: 查询的结果
          *   @Date: 17:09
         */
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
