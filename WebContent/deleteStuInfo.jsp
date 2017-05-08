<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.StuMsDAO" %>
<%
   String stuid=request.getParameter("stuid");
   StuMsDAO stuDao = new StuMsDAO();
   stuDao.deleteByStuId(stuid);
   stuDao.close();
%>

<jsp:forward page="studentList.jsp" />