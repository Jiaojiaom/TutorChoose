<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="javabean.TeacherMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.StudentMsDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑教师信息</title>
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
<!-- 表格控制 -->
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">修改教师信息</td>
	</tr>
	<%
	   String teacherid=request.getParameter("teacherid");
	   TeacherMsDAO teacherDao = new TeacherMsDAO();
       TeacherMsg teacherMsg = teacherDao.findByTeacherID(teacherid);
	 %>
    <form id="form1" name="form1" method="post" action="updateInfo">
    	<tr>
		    <td class="subtitle">工号： <input name="userType" type="hidden" value="teacher" /></td>
		     <td><input name="TeacherID" type="text" id="TeacherID" value=<%=teacherMsg.getTeacherID()%>></td>
		</tr>
    	<tr>
		    <td class="subtitle">姓名： </td>
		    <td><input name="TeacherName" type="text" id="TeacherName" value=<%=teacherMsg.getTeacherName()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">系： </td>
		    <td>
		       <select name="DeptID" id="DeptID" value=<%=teacherMsg.getDeptID()%>>
		      <%
			    DeptMsDAO deptDao = new DeptMsDAO();
				ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
				for (Map<String, String> deptMsg : deptMsgs) {	
			  %>
			     <option value =<%=deptMsg.get("deptid") %>><%=deptMsg.get("deptid") %></option>
			  <%
			    }
				deptDao.close();
		      %>
		      </select>
		     </td>
		</tr>
		<tr>
		    <td class="subtitle">密码： </td>
		    <td><input name="TPassword" type="password" id="TPassword" value=<%=teacherMsg.getTPassword()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">性别： </td>
		    <td> 
		      <%
			    if(teacherMsg.getSex().equals("F")){
			  %>
			    <input type="radio" name="Sex" value="M">男
			    <input type="radio" name="Sex" value="F" checked>女
			  <%
			    } else {
		      %>
		        <input type="radio" name="Sex" value="M" checked>男
			    <input type="radio" name="Sex" value="F">女
		      <%
			    } 
		      %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">职称：</td>
		    <td> 
              <%
			    if(teacherMsg.getTitle().equals("教授")){
			  %>
			    <input type="radio" name="Title" value="教授" checked>教授
                <input type="radio" name="Title" value="副教授" >副教授
			  <%
			    } else if(teacherMsg.getTitle().equals("副教授")) {
		      %>
		        <input type="radio" name="Title" value="教授">教授
                <input type="radio" name="Title" value="副教授" checked>副教授
		      <%
			    } else {
		      %>
		        <input type="radio" name="Title" value="教授">教授
                <input type="radio" name="Title" value="副教授">副教授
		      <%
			    } 
		      %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">学生人数: </td>
		    <td><input name="StudentCount" type="text" id="StudentCount" value=<%=teacherMsg.getStudentCount()%>></td>
		</tr>
		<tr>
		    <td class="Privilege">选择模式: </td>
		    <td>
		      <%
			    if(teacherMsg.getPrivilege()==0){
			  %>
			    <input type="radio" name="Privilege" value="0" checked>先到先得
                <input type="radio" name="Privilege" value="1">按照绩点排名
                <input type="radio" name="Privilege" value="2">反选
			  <%
			    } else if(teacherMsg.getPrivilege()==1) {
		      %>
		        <input type="radio" name="Privilege" value="0">先到先得
                <input type="radio" name="Privilege" value="1" checked>按照绩点排名
                <input type="radio" name="Privilege" value="2">反选
		      <%
			    } else if(teacherMsg.getPrivilege()==2) {
		      %>
		        <input type="radio" name="Privilege" value="0">先到先得
                <input type="radio" name="Privilege" value="1">按照绩点排名
                <input type="radio" name="Privilege" value="2" checked>反选
		      <%
			    }
		      %>
		    </td>
		</tr>
		<tr>
		    <td class="subtitle">电话: </td>
		    <td><input name="tel" type="text" id="tel" value=<%=teacherMsg.getTel()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">自我介绍： </td>
		    <td><textarea name="Intro" rows="3" cols="20" value=<%=teacherMsg.getIntro()%>><%=teacherMsg.getIntro()%></textarea></br></td>
		</tr>
		<tr>
		  <td align="center"><input type="submit" name="submit" value="保存" float="right"></td>
		<tr>
	</form>
</table>

</body>
</html>