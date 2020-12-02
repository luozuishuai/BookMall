package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:13
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book order by id desc";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Long count = (Long) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }

}
