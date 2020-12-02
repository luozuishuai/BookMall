package com.atguigu.pojo;

import java.math.BigDecimal;

/**
 * @author luozuishuai
 * @Created on 2020-11-27 17:09
 */
public class CartItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return getPrice().multiply(new BigDecimal(getCount()));

    }


    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {

        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = getPrice().multiply(new BigDecimal(getCount()));
    }

    public CartItem() {

    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
