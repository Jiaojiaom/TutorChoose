<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO" %>
<%
   String teacherid=request.getParameter("teacherid");
   TeacherMsDAO teacherDao = new TeacherMsDAO();
   teacherDao.deleteByTeacherId(teacherid);
   teacherDao.close();
%>

<jsp:forward page="teacherList.jsp" />