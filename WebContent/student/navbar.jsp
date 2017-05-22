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
	HttpSession h = request.getSession(); 
	if(h.getAttribute("stuId") != null){
		String stuId = (String)h.getAttribute("stuId");
		StudentDAO so = new StudentDAO();
		Map<String,String> ot = so.studentInfo(stuId);
		//学生状态
		String state = ot.get("choosedState");
	
%>
	
	<nav>
		<header>
				<div class="logo">
					TutorSEL
				</div>
				<div class="menu">
					<a href="homepage.jsp">导师列表</a>
					<a href="setting.jsp">个人设置</a>
				</div>
				<!-- <div class="welcome">欢迎进入：<%=ot.get("stuname")%>&nbsp;&nbsp;<a href="../login.jsp"><font color="red" size="1">退出</font></a></div> -->
				<div class="welcome">
					<div class="status">当前状态：
					<%if(ot.get("choosedstate").equals("0")){
						out.println("<font color='crimson' size='2'>未选择&nbsp;&nbsp;</font>");
					}else if(ot.get("choosedstate").equals("1")){
						out.println("<font color='blue' size='2'>待定&nbsp;&nbsp;</font>");
					}else if(ot.get("choosedstate").equals("2")){
						out.println("<font color='crimson' size='2'>淘汰&nbsp;&nbsp;</font>");
					}else{
						out.println("<font color='green' size='2'>成功&nbsp;&nbsp;</font>");
					}
					%>
					</div>
					欢迎进入：<%=ot.get("stuname")%>&nbsp;&nbsp;<a href="../login.jsp">
				<!-- <font color="crimson" size="1">退出</font></a></div> -->
				<font color="crimson" size="2">退出</font></a></div>
			</header>
	</nav>
	<%
	}
	else{
		//返回登录页面
		response.sendRedirect("../login.jsp");
	} %>
</body>
</html>