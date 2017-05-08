<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.StuMsDAO" %>
<%
   String stuid=request.getParameter("stuid");
   int chooseState=Integer.parseInt(request.getParameter("chooseState"));
   StuMsDAO stuDao = new StuMsDAO();
   stuDao.chooseStu(stuid,chooseState);
   stuDao.close();
%>

<jsp:forward page="studentList.jsp" />