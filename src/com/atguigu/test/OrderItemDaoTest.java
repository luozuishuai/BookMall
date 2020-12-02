package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 11:29
 */
public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"三体",3,new BigDecimal(79),new BigDecimal(79),"48484654156465748"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        for (OrderItem item : orderItemDao.queryOrderItemByOrderId("48484654156465748")) {
            System.out.println(item);
        }
    }
}