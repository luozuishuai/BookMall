<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
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
			<span class="wel_word">结算</span>
		<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		<%--如果商品库存不足--%>
		<c:if test="${sessionScope.orderId.contains('商品库存数量不足，请选择其他商品')}">
			<h1>${sessionScope.orderId}</h1>
		</c:if>

		<%--如果商品库存足够--%>
		<c:if test="${not sessionScope.orderId.contains('商品库存数量不足，请选择其他商品')}">
			<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		</c:if>


	
	</div>

	<%@include file="/pages/common/footer.jsp" %>
</body>
</html>