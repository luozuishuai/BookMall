package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 11:05
 */
public interface OrderItemDao {

    //保存订单项
    public int saveOrderItem(OrderItem orderItem);

    //根据订单号查询订单明细
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
