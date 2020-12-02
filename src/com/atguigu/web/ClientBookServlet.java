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

/**
 * @author luozuishuai
 * @Created on 2020-11-27 9:51
 */
public class ClientBookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数pageNo和PageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用page方法得到Page对象 获取页码数据和当前页面内容
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置url地址
        page.setUrl("client/bookServlet?action=page");
        //存入request域中
        request.setAttribute("page", page);
        //请求转发到图书列表页
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数pageNo和PageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //调用page方法得到Page对象 获取页码数据和当前页面内容
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //设置url地址
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(request.getParameter("min") != null){
            sb.append("&min=" + request.getParameter("min"));
        }
        if(request.getParameter("max") != null){
            sb.append("&max=" + request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //存入request域中
        request.setAttribute("page", page);
        //请求转发到图书列表页
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
}
