<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>导航栏</title>
<style type="text/css">
	body{
	margin: 0;
	padding: 0;
	background: #f1f2f4;
	width: 100%;
	height: 120vh;
	display: flex;
	flex-direction: column;
	align-items: center;
}
nav{
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 20;
    overflow: hidden;
    background: #fff;
    border-bottom: 1px solid rgba(30,35,42,.06);
    box-shadow: 0 1px 3px 0 rgba(0,34,77,.05);
    background-clip: content-box;
}
nav header{
    height: 50px;
	display: flex;
	align-items: center;
}
nav header .logo{
	color: #38b7ea;
    width: 10%;
    text-align: center;
    font-size: 25px;
	font-weight: 500;
}
nav header .menu{
	width: 20%;
    font-size: 15px;
    display: flex;
    justify-content: space-around;
}
nav header .menu a{
    color: #8590a6;
    text-decoration: none;
}
nav header .menu a:hover{
    color: #03A9F4;
    text-decoration: none;
}
nav header .welcome{
	width: 60%;
	display: flex;
	justify-content: flex-end;
	font-size: 15px;
}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
%>
	
	<nav>
		<header>
				<div class="logo">
					TutorSEL
				</div>
				<div class="menu">
					<a href="homepage.jsp">信息列表</a>
					<a href="setting.jsp">个人设置</a>
				</div>
				<div class="welcome">
					欢迎进入：<%=session.getAttribute("username")%>&nbsp;&nbsp;<a href="../login.jsp">
				<font color="crimson" size="2">退出</font></a></div>
			</header>
	</nav>
</body>
</html>