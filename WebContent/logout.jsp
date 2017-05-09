<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  
	session.removeAttribute("username");  // 清空 Session 变量  
	response.sendRedirect("login.jsp");  
%>  