<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/pages/common/head.jsp" %>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("您确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？")
            });
			$("#clearCart").click(function () {
				return confirm("您确定要清空购物车吗？")
            });
			$(".bookCount").change(function () {
				var id = $(this).attr("bookId");
				var bookName = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				if(confirm("您确定将【" + bookName + "】的数量修改为：" + count + "吗")){
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&id=" + id + "&count=" + count;
				}else{
				    this.value = this.defaultValue;
				}
            });
			$("#createOrder").click(function () {

            });

        });
	</script>
</head>
<body>
	
	<div id="header">
		<a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif"></a>
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<%--如果购物车是空的--%>
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a> </td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td><input class="bookCount" bookId="${item.value.id}" style="width: 50px;text-align: center" type="text" value="${item.value.count}"></td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">￥${sessionScope.cart.totalPrice}</span>元</span>
				<span id="clearCart" class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a id="createOrder" href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp" %>
</body>
</html>