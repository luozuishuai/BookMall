package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:09
 */
public interface BookDao {

    //添加图书
    public int addBook(Book book);

    //根据id删除指定图书
    public int deleteBookById(Integer id);

    //更新图书
    public int updateBook(Book book);

    //根据id查询图书
    public Book queryBookById(Integer id);

    //返回所有图书
    public List<Book> queryBooks();

    //返回总记录数
    public Integer queryForPageTotalCount();

    //返回当前页的数据
    public List<Book> queryForItems(int begin,int pageSize);

    //返回当前价格区间内的总记录数
    public int queryForPageTotalCountByPrice(int min,int max);

    List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize);

    //返回当前价格区间内当前页的数据
}
