package com.atguigu.filter;

import com.atguigu.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 16:36
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser != null) {
            if (loginUser.getId() > 10) {
                //不是管理员
                request.getRequestDispatcher("/index.jsp").forward(request, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        request.getRequestDispatcher("/pages/user/login.jsp").forward(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
