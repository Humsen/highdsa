<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>登录页面</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">

</head>

<body>
	<div class="container">
		<form class="form-signin" method="post" action="">
			<h3 class="form-signin-heading">欢迎登录highdsa后台系统</h3>
			<label for="inputEmail" class="sr-only">用户名</label> <input type="text" id="inputEmail" class="form-control" placeholder="用户名" name="username"
				required autofocus> <label for="inputPassword" class="sr-only">密码</label> <input type="password" id="inputPassword" class="form-control"
				placeholder="密码" name="password" required>
			<div class="checkbox">
				<label> <input type="checkbox" name="rememberMe"> 记住我
				</label>
			</div>
			<p class="bg-warning">${error}</p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->

</body>
</html>
