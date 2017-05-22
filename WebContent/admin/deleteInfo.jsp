<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="db.StudentMsDAO" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%
   String id=request.getParameter("id");
   String deleteType=request.getParameter("dataType");  
   if(deleteType.equals("student")){
	   StudentMsDAO studentDao = new StudentMsDAO();
	   studentDao.deleteByStuId(id);
	   studentDao.close();
   } else if(deleteType.equals("teacher")){
	   TeacherMsDAO teacherDao = new TeacherMsDAO();
	   teacherDao.deleteByTeacherId(id);
	   teacherDao.close();
   } else if(deleteType.equals("dept")){
	   DeptMsDAO deptDao = new DeptMsDAO();
	   deptDao.deleteByDeptId(id);
	   deptDao.close();
   } else if(deleteType.equals("class")){
	   ClassMsDAO classDao = new ClassMsDAO();
	   classDao.deleteByClassId(id);
	   classDao.close();
   }
   session.setAttribute("clickType", deleteType);
   response.sendRedirect("homepage.jsp");
%>