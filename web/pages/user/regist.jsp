<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
		<%@include file="/pages/common/head.jsp" %>
		<script type="text/javascript">
			$(function () {
			    //先给用户名输入栏绑定改变事件
				$("#username").blur(function () {
					var username = this.value;
					$.getJSON("${basePath}" + "userServlet","action=ajaxUsernameExist&username=" + username,function (data) {
						var result = data.result;
						if(result){
                            $("span.errorMsg").text("该用户名已存在");
						}else{
                            $("span.errorMsg").text("");
						}
                    })
                });
				//给邮箱输入栏绑定改变事件
				$("#email").blur(function () {
					var email = this.value;
					$.getJSON("${basePath}" + "userServlet","action=ajaxEmailExist&username=" + email,function (data) {
						var result = data.result;
						if(result){
                            $("span.errorMsg").text("该邮箱已存在");
						}else{
                            $("span.errorMsg").text("");
						}
                    })
                });
			    $("#code_img").click(function () {
					this.src = "${basePath}kaptchaServlet.jpg?" + new Date();
                });
				$("#sub_btn").click(function () {
					var username = $("#username").val();
					var usernamePatt = /^\w{5,12}$/;
					if(!usernamePatt.test(username)){
					    $("span.errorMsg").text("用户名不合理");
					    return false;
					}
					var password = $("#password").val();
                    var passwordPatt = /^[a-z0-9_-]{6,18}$/;
                    if(!passwordPatt.test(password)){
                        $("span.errorMsg").text("密码不合理");
                        return false;
                    }
					var repwd = $("#repwd").val();
                    if(password != repwd){
						$("span.errorMsg").text("两次输入的密码不一致");
						return false;
                    }
					var email = $("#email").val();
                    var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                    if(!emailPatt.test(email)){
                        $("span.errorMsg").text("邮箱不合理");
                        return false;
					}
					var code = $("#code").val();
                    code = $.trim(code);
                    if(code == null || code == ""){
                        $("span.errorMsg").text("验证码不能为空");
                        return false;
					}
                    $("span.errorMsg").text();

                });
            });
		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif"></a>
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
									value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
										<img id="code_img" alt="" src="kaptchaServlet.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp" %>
	</body>
</html>