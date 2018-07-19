<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<style type="text/css">
	input[type=text], input[type=password] {
		width: 150;
	}
</style>
</head>
<body>
	<form name="reg" action="/LoginAndLogOut/LoginServlet" method="post">
		用户名:<input name="username" type="text" /><br />
		密&nbsp;&nbsp;&nbsp;码:<input name="password" type="password" /><br />
		验证码:<input name="check_code" type="text" />
		<img src="/LoginAndLogOut/CheckServlet"><br />
		<input type="submit" value="提交" id="bt" />
	</form>
</body>
</html>