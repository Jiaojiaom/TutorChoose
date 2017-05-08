<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StuMsDAO" %>
<%@ page import="javabean.StuMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.TeacherMsDAO" %>
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
	    <td colspan="2" class="tableTitle">学生详细信息</td>
	</tr>
	<%
	   String stuid=request.getParameter("stuid");
	   StuMsDAO stuDao = new StuMsDAO();
       StuMsg stuMsg = stuDao.queryByStuId(stuid);
       System.out.println(stuMsg.getIntro());
	 %>
   	<tr>
	    <td class="subtitle">学号： </td>
	    <td><p><%=stuMsg.getStuID()%></p></td>
	</tr>
   	<tr>
	    <td class="subtitle">姓名： </td>
	    <td><p><%=stuMsg.getStuName()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">系： </td>
	    <td><p><%=stuMsg.getDeptID()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">班级： </td>
	    <td><p><%=stuMsg.getClassID()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">密码： </td>
	    <td><p><%=stuMsg.getSPassword()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">性别： </td>
	    <td><p><%=stuMsg.getSex()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">绩点: </td>
	    <td><p><%=stuMsg.getGrade()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">电话: </td>
	    <td><p><%=stuMsg.getTel()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">自我介绍： </td>
	    <td><textarea disabled><%=stuMsg.getIntro()%></textarea></td>
	</tr>
	<tr>
	    <td class="subtitle">导师姓名： </td>
	    <td><p><%=stuMsg.getTeacherID()%></p></td>
	</tr>
	<tr>
	    <td class="subtitle">状态： </td>
	    <td>
	        <%
			    if(stuMsg.getChoosedState()==0){
			%>
			    <p>未选</p>
			<%
			    } else if(stuMsg.getChoosedState()==1){
			%>
			    <p>待定</p>
			<%
			    } else if(stuMsg.getChoosedState()==1){
			%>
			    <p>成功</p>
			<%
			    } else if(stuMsg.getChoosedState()==1){
			%>
			    <p>淘汰</p>
			<%
			    } 
		    %>
	    </td>
	</tr>
	<tr>
	    <td class="subtitle">选择时间： </td>
	    <td><p><%=stuMsg.getSelectDate()%></p></td>
	</tr>
</table>
</hr>
<table style="margin-left:auto;margin-right:auto;">
   <thead>
     <th>导师姓名</th>
     <th>状态 </th>
     <th>选择时间</th>
   </thead>
   <tbody>
   <%
	    SelectTeacherDAO selectTeacherDao = new SelectTeacherDAO();
		ArrayList<Map<String, String>> selectTeacherMsgs = selectTeacherDao.querySelectTeacherAll();
		int i=0;
		for (Map<String, String> selectTeacherMsg : selectTeacherMsgs) {	
	%>
    <tr>
	    <td><p><%=selectTeacherMsg.get("teacherid") %></p></td>
	    <td> 
		    <%
			    if(selectTeacherMsg.get("choosestate").equals("0")){
			%>
			    <p>未选</p>
			<%
			    } else if(selectTeacherMsg.get("choosestate").equals("1")){
			%>
			    <p>待定</p>
			<%
			    } else if(selectTeacherMsg.get("choosestate").equals("2")){
			%>
			    <p>成功</p>
			<%
			    } else if(selectTeacherMsg.get("choosestate").equals("3")){
			%>
			    <p>淘汰</p>
			<%
			    } 
		    %>
      </td>
	  <td><p><%=selectTeacherMsg.get("selectdate") %></p></td>
	</tr>
	 <%
		}
	 %>
   </tbody>
</table>
</body>
</html>