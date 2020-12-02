package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:39
 */
public class BookServiceTest {

    private BookService tool = new BookServiceImpl();

    @Test
    public void addBook() {
        tool.addBook(new Book(null," 国哥在手，天下我有！", "1125", new BigDecimal(1000000),
                100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        tool.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        tool.updateBook(new Book(4,"社会我国哥，人狠话不多！", "1125", new BigDecimal(999999),
                10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(tool.queryBookById(3));
    }

    @Test
    public void queryBooks() {
        for(Book book : tool.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(tool.page(1,4));
    }
    @Test
    public void pageByPrice(){
        Page<Book> bookPage = tool.pageByPrice(3, 4, 10, 80);
        for (Book book : bookPage.getItems()) {
            System.out.println(book);
        }
    }
}