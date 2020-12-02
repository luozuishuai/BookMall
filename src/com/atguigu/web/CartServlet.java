package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author luozuishuai
 * @Created on 2020-11-27 17:36
 */
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面传入过来的商品id、名称、数量、单价
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        System.out.println(id);
        Book book = bookService.queryBookById(id);
        String name = book.getName();
        BigDecimal price = book.getPrice();

        CartItem cartItem = new CartItem(id, name, 1, price, price);
        //用addItem()添加CartItem到Cart
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            //将Cart对象存入Session中
            session.setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //将页面需要展示的购物车商品总数量和最后一个商品名称转为json传递给页面
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName",cartItem.getName());
        //Ajax传json给页面
        Gson gson = new Gson();
        String s = gson.toJson(map);
        session.setAttribute("cart",cartItem.getName());
        resp.getWriter().write(s);
    }
protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面传入过来的商品id、名称、数量、单价
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        String name = book.getName();
        BigDecimal price = book.getPrice();
        //用WebUtils封装为CartItem
        CartItem cartItem = new CartItem(id, name, 1, price, price);
        //用addItem()添加CartItem到Cart
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            //将Cart对象存入Session中
            session.setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //
        session.setAttribute("lastName",cartItem.getName());
        //重定向跳转回去
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //重定向跳转回去
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        //重定向跳转回去
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            //重定向跳转回去
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
