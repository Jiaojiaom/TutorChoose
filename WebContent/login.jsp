<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="stylesheet" type="text/css" href="lib/common.css">
</head>
<body>
	<form class="g-form" action="login" method="post">
	    <h2>登陆页面</h2>
        <input type="text" placeholder="UserName" name="adminId">
        <input type="password" placeholder="password" name="adminPassword">
        <div>
        	<input type="radio" class="radio"> 学生
        	<input type="radio" class="radio"> 教师
        	<input type="radio" class="radio"> 管理员
        </div>
        <input type="submit" value="登录" class="submit">
    </form>
</body>
</html>