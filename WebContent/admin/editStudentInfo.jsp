<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StudentMsDAO" %>
<%@ page import="javabean.StudentMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.TeacherMsDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑学生信息</title>
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
	    <td colspan="2" class="tableTitle">修改学生信息</td>
	</tr>
	<%
	   String stuid=request.getParameter("stuid");
	   StudentMsDAO studentDao = new StudentMsDAO();
       StudentMsg studentMsg = studentDao.findByStudentId(stuid);
       studentDao.close();
	 %>
    <form id="form1" name="form1" method="post" action="updateInfo">
        <input name="userType" type="hidden" value="student" /> 
    	<tr>
		    <td class="subtitle">学号:</td>
		     <td><input name="StuID" type="text" id="StuID" value=<%=studentMsg.getStuID()%>></td>
		</tr>
    	<tr>
		    <td class="subtitle">姓名： </td>
		    <td><input name="StuName" type="text" id="StuName" value=<%=studentMsg.getStuName()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">系： </td>
		    <td>
		       <select name="DeptID" id="DeptID" value=<%=studentMsg.getDeptID()%>>
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
		    <td class="subtitle">班级： </td>
		    <td>
		       <select name="ClassID" id="ClassID" value=<%=studentMsg.getClassID()%>>
		      <%
			    ClassMsDAO classDao = new ClassMsDAO();
				ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll();
				for (Map<String, String> classMsg : classMsgs) {	
			  %>
			     <option value =<%=classMsg.get("classid") %>><%=classMsg.get("classid") %></option>
			  <%
			    }
				classDao.close();
		      %>
		      </select>
		     </td>
		</tr>
		<tr>
		    <td class="subtitle">密码： </td>
		    <td><input name="SPassword" type="password" id="SPassword" value=<%=studentMsg.getSPassword() %>></td>
		</tr>
		<tr>
		    <td class="subtitle">性别： </td>
		    <td> 
		      <% if(studentMsg.getSex().equals("F")){ %> 
			    <input type="radio" name="Sex" value="M">男
			    <input type="radio" name="Sex" value="F" checked>女
			  <% } else { %>
		        <input type="radio" name="Sex" value="F" checked>男
			    <input type="radio" name="Sex" value="M">女
		      <% } %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">绩点: </td>
		    <td><input name="Grade" type="text" id="Grade" value=<%=studentMsg.getGrade()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">电话: </td>
		    <td><input name="tel" type="text" id="tel" value=<%=studentMsg.getTel()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">自我介绍： </td>
		    <td><textarea name="Intro" rows="3" cols="20"><%=studentMsg.getIntro()%></textarea></br></td>
		</tr>
		<tr>
		    <td class="subtitle">导师编号：  </td>
		    <td>
		     <select name="TeacherID" id="TeacherID" value=<%=studentMsg.getTeacherID()%>>
		      <%
			    TeacherMsDAO teacherDao = new TeacherMsDAO();
				ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherList();
				for (Map<String, String> teacherMsg : teacherMsgs) {	
			  %>
			     <option value =<%=teacherMsg.get("teachername") %>><%=teacherMsg.get("teachername") %></option>
			  <%
			    }
				deptDao.close();
		      %>
		      </select>
		    </td>
		</tr>
		<tr>
		    <td class="subtitle">状态： </td>
		    <td> 
			    <%
				    if(studentMsg.getChoosedState()==0){
				%>
				    <input type="radio" name="ChooseState" value=0 checked>未选
				    <input type="radio" name="ChooseState" value=1 >待定
				    <input type="radio" name="ChooseState" value=2 >成功
				    <input type="radio" name="ChooseState" value=3 >淘汰
				<%
				    } else {
			    %>
			        <input type="radio" name="ChooseState" value=0 >未选
				    <input type="radio" name="ChooseState" value=1 checked>待定
				    <input type="radio" name="ChooseState" value=2 >成功
				    <input type="radio" name="ChooseState" value=3 >淘汰
			    <%
				    } 
			    %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">选择时间：  </td>
		    <td><input name="SelectDate" rows="3" cols="20" value=<%=studentMsg.getSelectDate()%>></br></td>
		</tr>
		<tr>
		  <td align="center"><input type="submit" name="submit" value="保存修改" float="right"></td>
		<tr>
	</form>
</table>
</body>
</html>