package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:26
 */
public class BookDaoTest {

    private BookDao tool = new BookDaoImpl();

    @Test
    public void addBook() {
        tool.addBook(new Book(null,"花儿为什么这样红","佚名",new BigDecimal(999),1100,264,null));
    }

    @Test
    public void deleteBookById() {
        tool.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        tool.updateBook(new Book(4,"小炒肉盖饭","大胖",new BigDecimal(18),897,49,null));
    }

    @Test
    public void queryBookById() {
        Book book = tool.queryBookById(4);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = tool.queryBooks();
        for(Book book : bookList){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(tool.queryForPageTotalCount());
    }

    @Test
    public void queryForItems() {
        for (Book book : tool.queryForItems(8, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(tool.queryForPageTotalCountByPrice(10,80));
    }

    @Test
    public void queryForItemsByPrice() {
        for (Book book : tool.queryForItemsByPrice(10, 80, 3, 4)) {
            System.out.println(book);
        }
    }
}