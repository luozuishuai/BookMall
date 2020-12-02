package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author luozuishuai
 * @Created on 2020-11-25 17:07
 */
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //跳转到首页
        response.sendRedirect(request.getContextPath());
    }

    protected void ajaxUsernameExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean exist = userService.existsUsername(username);
        booleanWriteToJson(exist,request,response);
    }


    protected void ajaxEmailExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean exist = userService.existsUsername(email);
        booleanWriteToJson(exist,request,response);
    }

    private void booleanWriteToJson(Boolean exist,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Boolean> map = new HashMap<>();
        Gson gson = new Gson();
        if(exist){
            map.put("result",exist);
            String s = gson.toJson(map);
            response.getWriter().write(s);
        }else{
            map.put("result",exist);
            String s = gson.toJson(map);
            response.getWriter().write(s);
        }
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            //如果loginUser为空则说明登录失败 提示错误信息返回给用户
            request.setAttribute("msg", "用户名或密码错误，请重新输入");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user",loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }


    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        request.setAttribute("username", username);
        request.setAttribute("email", email);

        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //2.检查验证码是否正确
        if (token !=null && token.equalsIgnoreCase(code)) {
            //3.检查用户名是否存在
            if (userService.existsUsername(username)) {
                //返回true则表示用户名已存在
                request.setAttribute("msg", "用户名[" + username + "]已存在");

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else if (userService.existsEmail(email)) {
                request.setAttribute("msg", "该邮箱已存在");

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                User loginUser = new User(null, username, password, email);
                userService.registUser(loginUser);
                //4.跳到注册成功页面
                request.getSession().setAttribute("user",loginUser);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }
}
