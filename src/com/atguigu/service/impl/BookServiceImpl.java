package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:37
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page page = new Page();

        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求总条目数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总条目数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //获取当前页码数据内容
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForItems(begin, pageSize);
        //设置数据内容
        page.setItems(items);

        return page;

        //求三个属性：总记录数，总页码，当前页数据
        //总记录数

        /*
        int pageTotalCount = bookDao.queryForPageTotalCount();

        //总页码数：
        if((pageTotalCount%pageSize)==0){
            int pageTotal = pageTotalCount/pageSize;
        }else{
            int pageTotal = (int) Math.ceil(pageTotalCount/pageSize);
        }
        //当前页码数据：
        List<Book> items = bookDao.queryForItems(pageNo, pageSize);

        return new Page(pageNo,pageTotalCount,pageTotalCount,pageSize,items);
        */
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();

        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求总条目数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        //设置总条目数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //获取当前页码数据内容
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForItemsByPrice(min, max, begin, pageSize);
        //设置数据内容
        page.setItems(items);

        return page;
    }
}
