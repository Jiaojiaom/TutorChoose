<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="connDB" scope="page" class="db.DBConnection"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="lib/common.css">
</head>
<body>
<form name="registerForm" class="g-form" action="register" method="post">
       <h2>注册页面</h2>
       <input type="text" placeholder="输入用户名" name="adminName">
       <input type="password" placeholder="输入密码" name="adminPassword">
       <input type="password" placeholder="重复密码" name="adminPassword2">
       <div>
       	<input type="radio" class="radio" name="userType"> 学生
       	<input type="radio" class="radio" name="userType"> 教师
       	<input type="radio" class="radio" name="userType"> 管理员
       </div>
       <input type="submit" value="注册" class="submit">
   </form>
</body>
</html>