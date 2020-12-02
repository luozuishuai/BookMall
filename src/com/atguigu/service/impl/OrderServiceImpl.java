package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 12:38
 */
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        //用当前时间戳+用户的id组合为订单号
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建订单对象 保存到Order中
        orderDao.saveOrder(new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId));


        //遍历购物车中的所有商品
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //转化为OrderItem商品项
            CartItem cartItem = entry.getValue();

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            //计算库存
            int newStock = book.getStock() - cartItem.getCount();
            if (newStock > -1) {
                //减少库存
                book.setStock(newStock);
                //增加销量
                book.setSales(book.getSales() + cartItem.getCount());

                bookDao.updateBook(book);
            }else {
                orderDao.deleteOrder(orderId);
                return "您购买的【" + cartItem.getName() + "】商品库存数量不足，请选择其他商品";
            }

            //添加到OrderItem
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

        }

        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }

    @Override
    public int deleteOrder(String orderId) {
        return orderDao.deleteOrder(orderId);
    }
}
