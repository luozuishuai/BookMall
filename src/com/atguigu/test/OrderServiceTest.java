package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 12:51
 */
public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"母猪",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"母猪",2,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"母猪2",3,new BigDecimal(20),new BigDecimal(10)));

        orderService.createOrder(cart,16);
    }

    @Test
    public void showAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("160653950066616");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("160653950066616");
        for (OrderItem item : orderItems) {
            System.out.println(item);
        }
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = orderService.showMyOrders(16);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("160653950066616");
    }
}