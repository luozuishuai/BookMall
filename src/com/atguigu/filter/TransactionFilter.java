package com.atguigu.filter;

import com.atguigu.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author luozuishuai
 * @Created on 2020-11-28 18:39
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
