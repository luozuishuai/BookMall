package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 12:28
 */
public interface OrderService {

    //将购物车中的商品生成订单
    public String createOrder(Cart cart,Integer userId);

    //查询全部订单（管理员）
    public List<Order> showAllOrders();

    //发货（管理员）
    public void sendOrder(String orderId);

    //查看订单详情（管理员/用户）
    public List<OrderItem> showOrderDetail(String orderId);

    //查看我的订单（用户）
    public List<Order> showMyOrders(Integer userId);

    //签收订单/确认收货（用户）
    public void receiveOrder(String orderId);

    //删除订单（管理员）
    public int deleteOrder(String orderId);
}
