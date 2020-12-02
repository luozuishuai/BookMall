<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Luozuishuai
  Date: 2020-11-25
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:if test="${empty sessionScope.user}">
        <%--如果用户未登录--%>
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="index.jsp">返回首页</a> &nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <%--如果用户已登录--%>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="orderServlet?action=showMyOrders">我的订单</a>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    </c:if>

</div>
</body>
</html>
