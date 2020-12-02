package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 10:58
 */
public interface OrderDao {

    //提交订单
    public int saveOrder(Order order);

    //查询所有订单
    public List<Order> queryOrders();

    //改变订单状态
    public int changeOrderStatus(String orderId,Integer status);

    //根据用户编号查询订单信息
    public List<Order> queryOrderByUserId(Integer userId);

    int deleteOrder(String orderId);
}
