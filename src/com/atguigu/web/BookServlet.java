package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-11-26 11:54
 */
public class BookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo+=1;
        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳转到图书列表页面 "/manager/bookServlet?action=list"
        //request重定向会出来刷新页面重复添加的bug（一次请求）
//        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);
//        System.out.println(request.getContextPath());
        //response重定向是跳转到端口号后面 需要手动加上工程名（两次请求）
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数的id，图书编号
        String id = request.getParameter("id");
        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(Integer.valueOf(id));
        //3.重定向回图书管理页面 /book/manager/bookService?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book book = bookService.queryBookById(Integer.valueOf(id));
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数===封装为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用BookService.updateBook(Book book);修改图书
        bookService.updateBook(book);
        //3.重定向回图书列表管理页面 地址：/工程名/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        request.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求参数pageNo和PageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),Page.PAGE_SIZE);
        //调用page方法得到Page对象 获取页码数据和当前页面内容
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置url地址
        page.setUrl("manager/bookServlet?action=page");
        //存入request域中
        request.setAttribute("page",page);
        //请求转发到图书列表页
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

}
