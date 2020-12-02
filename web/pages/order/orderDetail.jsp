<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
		<a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif"></a>

		<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单号</td>
				<td>书名</td>
				<td>数量</td>
				<td>金额</td>
				<td>总金额</td>
			</tr>

			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.name}</td>
					<td>${order.count}</td>
					<td>${order.price}</td>
					<td>${order.totalPrice}</td>

				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp" %>
</body>
</html>