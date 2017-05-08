<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StuMsDAO" %>
<%@ page import="javabean.StuMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="javabean.TeacherMsg" %>
<%@ page import="db.StuMsDAO" %>
<%@ page import="db.SelectTeacherDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.tableTitle{
	   text-align:center;
	   font-size:24px;
	   font-weight:bold;
	   border-bottom:1px dotted blue;
    }
    .subtitle {
	   font-weight:bold;
	   text-align:right;
	}
	td {
	   height:40px;
	}
</style>
</head>
<body>
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">教师详细信息</td>
	</tr>
	<%
	   String teacherid=request.getParameter("teacherid");
	   TeacherMsDAO teacherDao = new TeacherMsDAO();
	   TeacherMsg teacherMsg = teacherDao.queryByTeacherId(teacherid);
       System.out.println(teacherMsg.getIntro());
	 %>
   	<tr>
	    <td class="subtitle">工号： </td>
	    <td><p><%=teacherMsg.getTeacherID()%></p></td>
	</tr>
   	<tr>
	    <td class="subtitle">姓名： </td>
	    <td><p><%=teacherMsg.getTeacherName()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">系： </td>
	    <td><p><%=teacherMsg.getDeptID()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">性别： </td>
	    <td><p><%=teacherMsg.getSex()%></p></td>
	</tr>
	<tr>
	<tr>
	    <td class="subtitle">职称： </td>
	    <td>
	        <%
			    if(teacherMsg.getTitle().equals("教授")){
			%>
			    <p>教授</p>
			<%
			    } else if(teacherMsg.getTitle().equals("副教授")){
			%>
			    <p>副教授</p>
			<%
			    } 
			%>
	    </td>
	</tr>
	<tr>
	    <td class="subtitle">学生人数： </td>
	    <td><p><%=teacherMsg.getStudentCount()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">选择规则： </td>
	    <td>
	        <%
			    if(teacherMsg.getPrivilege()==0){
			%>
			    <p>先到先得</p>
			<%
			    } else if(teacherMsg.getPrivilege()==1){
			%>
			    <p>按照绩点排名</p>
			<%
			    } else if(teacherMsg.getPrivilege()==2){
			%>
			    <p>具有反选资格</p>
			<%
			    } 
			%>
	    </td>
	</tr>
	<tr>
	    <td class="subtitle">电话: </td>
	    <td><p><%=teacherMsg.getTel()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">自我介绍： </td>
	    <td><textarea><%=teacherMsg.getIntro()%></textarea></td>
	</tr>
</table>
</hr>
<table class="datalist">
	<caption>学生信息</caption>
	<tr>
		<th style="width: 6%;">序号</th>
		<th>姓名</th>
		<th>绩点</th>
		<th>状态</th>
		<th>选择时间</th>
	</tr>
	  <%    
	    StuMsDAO stuDao = new StuMsDAO();
		ArrayList<Map<String, String>> stuMsgs = stuDao.queryByTeacherId(teacherid);
		int i=0;
		for (Map<String, String> stuMsg : stuMsgs) {	
	  %>
	<tr>
		<td style="width: 6%;"><%=++i %></td>
		<td><a href="studentDetail.jsp?stuid=<%=stuMsg.get("stuid") %>"><%=stuMsg.get("stuname") %></a></td>
		<td><%=stuMsg.get("grade") %></td>
		<td><%=stuMsg.get("choosedstate") %></td>
		<td><%=stuMsg.get("selectdate") %></td>
	</tr>
		<%
		}
	    %>
	 <tr>
	     <a href="excel.jsp?exportToExcel=YES">Export to Excel</a>
	 </tr>
</table>
</body>
</html>