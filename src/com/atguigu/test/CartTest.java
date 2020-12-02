package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-27 18:34
 */
public class CartTest {

    Cart cart = new Cart();

    @Test
    public void addItem() {
        System.out.println(cart);
        cart.addItem(new CartItem(1,"母猪",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"母猪",2,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"母猪2",3,new BigDecimal(20),new BigDecimal(10)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        System.out.println(cart);
        cart.addItem(new CartItem(1,"母猪",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"母猪",2,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"母猪2",3,new BigDecimal(20),new BigDecimal(10)));
        System.out.println(cart);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        System.out.println(cart);
        cart.addItem(new CartItem(1,"母猪",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"母猪",2,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"母猪2",3,new BigDecimal(20),new BigDecimal(10)));
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {

        System.out.println(cart);
        cart.addItem(new CartItem(1,"母猪",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"母猪",2,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"母猪2",3,new BigDecimal(20),new BigDecimal(10)));
        System.out.println(cart);
        cart.updateCount(2,5);
        System.out.println(cart);


    }
}