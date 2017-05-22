<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.URLEncoder"%>
<%
String teacherId = request.getParameter("teacherid");
HttpSession s = request.getSession(); 
if(s.getAttribute("stuId") != null){
	String stuId = (String)s.getAttribute("stuId");
	StudentDAO so = new StudentDAO();
	int num = so.cancelTeacher(stuId,teacherId);
	if(num > 0){
		if(num == 3){
			String result = "导师已确定无法取消"; 
			session.setAttribute("result", result);
			session.setAttribute("isError", "1");
			response.sendRedirect("detail.jsp?teacherid="+teacherId);
		}
		else{
		String result = "取消成功"; 
		session.setAttribute("result", result);
		session.setAttribute("isError", "0");
		response.sendRedirect("detail.jsp?teacherid="+teacherId);
		}
	}
	else{
		String result = "取消失败"; 
		session.setAttribute("result", result);
		session.setAttribute("isError", "1");
		response.sendRedirect("detail.jsp?teacherid="+teacherId);
	}
}
else{
	response.sendRedirect("login.jsp");
}
%>