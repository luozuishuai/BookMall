package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author luozuishuai
 * @Created on 2020-11-24 15:14
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
//      FileInputStream fis = new FileInputStream(new File("jdbc.properties"));
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        /**
         *   @Author:luozuishuai
         *   @Desciption:从数据库连接池中获取一个连接（储存到ThreadLocal中）
         *   @params: none
         @return:返回连接池中的一个连接对象,如果返回null则说明数据库连接失败
          *   @Date: 2020-11-24 15:25
         */
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollbackAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

//      public static Connection getConnection(){
//        /**
//        *   @Author:luozuishuai
//        *   @Desciption:从数据库连接池中获取一个连接
//        *   @params: none
//            @return:返回连接池中的一个连接对象,如果返回null则说明数据库连接失败
//        *   @Date: 2020-11-24 15:25
//        */
//        Connection conn = null;
//        try {
//            conn = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }

//    public static void close(Connection conn){
//        /**
//        *   @Author:luozuishuai
//        *   @Desciption:关闭连接，返回数据库连接池
//        *   @params: * @param conn
//            @return:
//        *   @Date:  15:26
//        */
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
